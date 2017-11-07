SUMMARY = "gbm library"
LICENSE = "MIT"
SECTION = "libs"

LIC_FILES_CHKSUM = "file://gbm.c;beginline=4;endline=22;md5=5cdaac262c876e98e47771f11c7036b5"

SRCREV = "d5a58c689932d42add1301c5cd323da5244374af"
SRC_URI = "git://github.com/thayama/libgbm;protocol=git;branch=master"

SRC_URI_append = " \
	file://0001-Add-gbm_bo_get_fd-function.patch \
"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(r8a7790|r8a7791|r8a7793|r8a7794)"
DEPENDS = "wayland-kms"

inherit autotools pkgconfig

# FILES_${PN} += "${libdir}/gbm/libgbm_kms.so.*"
FILES_${PN} = "${libdir}/libgbm.so.* ${libdir}/gbm/libgbm_kms.so.*"
FILES_${PN}-dev += "${libdir}/gbm/*.so ${libdir}/gbm/*.la"
FILES_${PN}-dev += "${libdir}/gbm/*.so ${libdir}/gbm/*.la"
FILES_${PN}-dbg += "${libdir}/gbm/.debug/*"
FILES_${PN}-staticdev += "${libdir}/gbm/*.a"

