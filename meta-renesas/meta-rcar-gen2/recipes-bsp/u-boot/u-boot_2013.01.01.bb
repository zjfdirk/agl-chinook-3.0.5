require recipes-bsp/u-boot/u-boot.inc

# This is needs to be validated among supported BSP's before we can
# make it default
DEFAULT_PREFERENCE = "-1"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

PV = "v2013.01.01+git${SRCPV}"

COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager|porter|silk)"

SRCREV = "b653737dfca271d2f3d96cf02c67cabef6112dab"
SRC_URI = "git://git.denx.de/u-boot-sh.git;branch=renesas/bsp/rcar-gen2-1.9.2;protocol=git"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Some workaround for gcc5 support (tbc):
SRC_URI_append = " \
	file://0001-fixup-build-with-gcc5.patch \
	file://0001-inline-use-the-gcc-inline-version-instead-of-the-c99.patch \
"

SRC_URI_append_porter = " \
	file://0001-uboot-Silk-board-support.patch \
	file://0004-uboot-porter-board-support.patch \
	file://0005-uboot-serial-sh-SCIF-internal-clock-support.patch \
	file://0006-uboot-Silk-disable-dcache-until-fixed.patch \
"
SRC_URI_append_silk = " \
	file://0001-uboot-Silk-board-support.patch \
	file://0004-uboot-porter-board-support.patch \
	file://0005-uboot-serial-sh-SCIF-internal-clock-support.patch \
	file://0006-uboot-Silk-disable-dcache-until-fixed.patch \
"

# MiniMonitor requires u-boot.srec
UBOOT_SREC ?= "u-boot.srec"
UBOOT_SREC_SYMLINK ?= "u-boot-${MACHINE}.srec"
UBOOT_SREC_IMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.srec"

do_deploy_append() {
	install ${S}/${UBOOT_SREC} ${DEPLOYDIR}/${UBOOT_SREC_IMAGE}

	cd ${DEPLOYDIR}
	rm -f ${UBOOT_SREC} ${UBOOT_SREC_SYMLINK}
	ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC}
	ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC_SYMLINK}
}
