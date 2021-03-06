meta-sota
=========

This layer enables over-the-air updates with OSTree and RVI SOTA client.

[OSTree](https://github.com/ostreedev/ostree) is a tool for atomic full file
system upgrades with rollback capability.  
Main advantage of OSTree compared 
to traditional dual partition model is that OSTree minimizes network bandwidth
and data storage footprint by sharing files with the same contents across file
system deployments.

[RVI SOTA client](https://github.com/advancedtelematic/rvi_sota_client) adds
authentication and provisioning capabilities to OTA and is integrated with
OSTree.

Build
-----

With AGL you can just add agl-sota feature while configuring your build
environment as in:

```bash
source meta-agl/scripts/aglsetup.sh -m porter agl-demo agl-appfw-smack agl-devel agl-sota
```

you can then just run:

```bash
bitbake agl-demo-platform
```

and get as a result "ostree_repo" folder in your images directory
(tmp/deploy/images/${MACHINE}/ostree_repo) containing your OSTree repository
with rootfs committed as an OSTree deployment, 'otaimg' bootstrap image which
is an OSTree physical sysroot as a burnable filesystem image and optionally
some machine-dependent live images (e.g. '*.rpi-sdimg-ota' for Raspberry Pi or
'*.porter-sdimg-ota' Renesas Porter board).

Although aglsetup.sh hooks provide reasonable defaults for SOTA-related
variables you may want to tune some of them.

SOTA-related variables in local.conf
------------------------------------

* OSTREE_REPO - path to your OSTree repository.  
  Defaults to "${DEPLOY_DIR_IMAGE}/ostree_repo"
* OSTREE_BRANCHNAME - the branch your rootfs will be committed to.  
  Defaults to "agl-ota"
* OSTREE_OSNAME - OS deployment name on your target device.  
  For more information about deployments and osnames see
  [OSTree documentation](https://ostree.readthedocs.io/en/latest/manual/deployment/)
  Defaults to "agl".
* OSTREE_INITRAMFS_IMAGE - initramfs/initrd image that is used as a proxy while
  booting into OSTree deployment. Do not change this setting unless you are
  sure that your initramfs can serve as such proxy.
* OSTREE_PUSH_CREDENTIALS - when set adds pushing your ostree commit to a remote
  repo. sota-tools will then use credentials in the file pointed to by this
  variable.

Usage
-----

### OSTree ###
OSTree includes its own simple http server.  
It just exposes the whole OSTree
repository to the network so that any remote device can pull data from it to
device's local repository.  
To use OSTree http server you need OSTree installed
on your build machine. Alternatively, you could run version built inside Yocto
using bitbake's [devshell](http://www.openembedded.org/wiki/Devshell).

To expose your repo run ostree trivial-httpd using any free port.

```bash
ostree trivial-httpd tmp/deploy/images/qemux86-64/ostree_repo -P 57556
```

You can then run from inside your device or QEMU emulation, provided your
network is set up correctly.

```bash
# agl-remote identifies the remote server in your local repo
ostree remote add --no-gpg-verify agl-remote http://192.168.7.1:57556 agl-ota-raspberrypi3
    
# agl-ota-raspberrypi3 is a branch name in the remote repo, set in OSTREE_BRANCHNAME
#   default value for OSTREE_BRANCHNAME is "agl-ota-${MACHINE}"
ostree pull agl-remote agl-ota-raspberrypi3
    
# agl is OS name as set in OSTREE_OSNAME
ostree admin deploy --os=agl agl-remote:agl-ota-raspberrypi3
```

After restart you should boot into the newly deployed OS image.

E.g. for the raspberrypi3 you can try this sequence:

```bash
# add remote
ostree remote add --no-gpg-verify agl-snapshot https://download.automotivelinux.org/AGL/snapshots/master/latest/raspberrypi3/deploy/images/raspberrypi3/ostree_repo/ agl-ota
 
# pull
ostree pull agl-snapshot agl-ota-raspberrypi3
    
# deploy
ostree admin deploy --os=agl agl-snapshot:agl-ota-raspberrypi3
```

### SOTA tools ###
SOTA tools now contains only one tool, garage-push that lets you push the
changes in OSTree repository generated by bitbake process.  
It communicates with an http server capable of querying files with HEAD requests and uploading them
with POST requests.  
Garage-push is used as following:

```bash
garage-push --repo=/path/to/ostree-repo --ref=mybranch --credentials=~/.sota-tools.json --user=username --password=
```

You can set OSTREE_PUSH_CREDENTIALS in your local.conf to make your build
results be automatically synchronized with a remote server.  
Credentials are stored in JSON format which is described in [sota-tools documentation](https://github.com/advancedtelematic/sota-tools/blob/master/README.adoc)
