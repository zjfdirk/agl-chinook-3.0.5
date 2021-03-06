SUMMARY     = "Phone for CES2017 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating phone on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/phone"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/phone;protocol=http"
SRCREV  = "${AUTOREV}"

# Pinned branch/SRCREV for Charming Chinook
SRC_URI_chinook = "git://gerrit.automotivelinux.org/gerrit/apps/phone;protocol=http;branch=chinook"
SRCREV_chinook = "a87aaf9b9d8c3eeae0e9006e4c73d17d03a55b93"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2 homescreen"

inherit qmake5 aglwgt
