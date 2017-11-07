SUMMARY = "ALSA sound configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=3d5968ae2c5badd70c24075cfe58cc1e"

RDEPENDS_${PN} = "alsa-utils"

COMPATIBLE_MACHINE = "(porter|koelsch)"

FILESEXTRAPATHS_prepend = "${THISDIR}/alsa-utils-config:"

SRC_URI = "file://COPYING \
          "

ASOUNDSTATEFILE_porter  = "asound.state-porter"
ASOUNDSTATEFILE_koelsch = "asound.state-porter"
#ASOUNDSTATEFILE_silk   = "asound.state-silk"

SRC_URI_append  = " file://${ASOUNDSTATEFILE}"

# package with no tarball
S = "${WORKDIR}"

do_install() {
       install -d ${D}/${localstatedir}/lib/alsa
       install -m 0644 ${ASOUNDSTATEFILE} ${D}/${localstatedir}/lib/alsa/asound.state
}

FILES_${PN} += "${localstatedir}"
