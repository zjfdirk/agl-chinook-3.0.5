DESCRIPTION = "AVB MSE Driver for Linux for the R-Car Gen3"

require include/avb-control.inc

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=192063521ce782a445a3c9f99a8ad560 \
"

inherit module

DEPENDS = "linux-renesas"

REQUIRED_DISTRO_FEATURES = "avb"

SRC_URI = "git://github.com/renesas-rcar/avb-mse.git;branch=rcar-gen3"
SRCREV = "9f090d1faccc79b59de4ec4d9ff313368df17ca1"

S = "${WORKDIR}/git"
