DESCRIPTION = "Build networking driver for MOST"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV = "0.1"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/most;protocol=https"
SRC_URI_append_porter = " file://0001-aim-network-backport-patch-for-3.10.31-ltsi.patch"

S = "${WORKDIR}/git/driver/${PN}"
SRCREV = "${AUTOREV}"

# Pinned branch/SRCREV for Charming Chinook
SRC_URI_chinook = "git://gerrit.automotivelinux.org/gerrit/src/most;protocol=https;branch=chinook"
SRCREV_chinook = "61ddb0d8f200af2da56f0922ffabfa7c5627ad15"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

do_install_append () {
    # modprobe automatically at boot
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${sysconfdir}/modules-load.d
        echo "aim_network" > ${D}${sysconfdir}/modules-load.d/aim_network.conf
    fi
}
