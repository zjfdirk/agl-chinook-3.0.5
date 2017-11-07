USB Gadget
==========

# Introduction

This README describes a way the USB Gadget feature can be used on Renesas Porter boards.

## Hardware setup

To use the USB Gadget feature, you need an extra micro-USB cable.
The proposed setup is based on the selection of USB OTG feature by configuring the jumper JP13 between 1-2 ("Host"). Then, you can plug the cable in CN22 connector ("USB Channel 0").

## Software setup

Be sure you have the "usbgadget" feature in the DISTRO_FEATURES bitbake environment variable.

# Usage

## Manual configuration

Example to uses Ethernet over USB feature:

    # modprobe g_ether
    # ip a | grep -A 2 usb0
    5: usb0: <BROADCAST,MULTICAST> mtu 1500 qdisc noop state DOWN group default qlen 1000
        link/ether 4e:bc:d4:ab:7e:e5 brd ff:ff:ff:ff:ff:ff
    # ip address add 192.168.0.10 dev usb0

Now, from your host computer you can check connectivity:

    $ ping 192.168.0.10
    PING 192.168.0.10 (192.168.0.10) 56(84) bytes of data.
    64 bytes from 192.168.0.10: icmp_seq=1 ttl=64 time=0.370 ms
    64 bytes from 192.168.0.10: icmp_seq=2 ttl=64 time=0.406 ms
    ...

## Persistent configuration

We can load the module at startup, by creating a file `/etc/modules-load.d/g_ether.conf`.

The file content should be as below:

    # Load g_ether at boot
    g_ether

Connman can handle the Ethernet over USB link. First, we create the directory which
contain the configuration file:

    # mkdir /etc/connman

Then we can edit a `/etc/connman/main.conf` file:

    [General]
    PreferredTechnologies=ethernet,wifi,cellular
    PersistentTetheringMode=true
    TetheringTechnologies=gadget

