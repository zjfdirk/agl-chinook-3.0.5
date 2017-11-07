require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

EXTRA_OEMAKE = "-e MAKEFLAGS="
KORG_ARCHIVE_COMPRESSION = "xz"
COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager|porter|silk)"
PV_append = "+git${SRCREV}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "165e12ce2d7839e755debbec78dfa43b54345275"
SRC_URI = "${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.9.7 \
          file://scripts-Makefile.headersinst-install-headers-from-sc.patch \
          file://0001-kernel-add-support-for-gcc-5.patch \
"
S = "${WORKDIR}/git"
