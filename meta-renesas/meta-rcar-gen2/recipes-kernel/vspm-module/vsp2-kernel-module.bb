require ../../include/rcar-gen2-modules-common.inc

LICENSE = "GPLv2&MIT"
LIC_FILES_CHKSUM = " \
	file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
	file://MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"

DEPENDS = "linux-renesas vspm-kernel-module"
PN = "vsp2-kernel-module"
PR = "r0"

SRCREV = "c231aff0fba0a2c559968098e5573050a1aa336d"
SRC_URI = " \
	git://github.com/renesas-devel/vsp2driver.git;protocol=git;branch=RCAR-GEN2/1.0.0 \
"
S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile() {
    export VSP2_VSPMDIR=${KERNELSRC}/include
    export VSP2_VSPMSYMVERS=vspm.symvers
    cd ${S}/drv
    make all ARCH=arm
}

do_install() {
    # Create destination folder
    mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/extra/ ${D}/usr/src/kernel/include/

    # Copy kernel module
    cp -f ${S}/drv/vsp2.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Copy shared library for reference from other modules
    cp -f ${S}/drv/Module.symvers ${D}/usr/src/kernel/include/vsp2.symvers
    cp -f ${S}/drv/Module.symvers ${KERNELSRC}/include/vsp2.symvers
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/vsp2.ko \
    ${sysconfdir}/* \
"

FILES_${PN}-dev = " \
  /usr/src/kernel/include/vsp2.symvers \
"
RPROVIDES_${PN} += "vsp2-kernel-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

ALLOW_EMPTY_kernel-module-vsp2 = "1"

# Autoload VSP2Driver
KERNEL_MODULE_AUTOLOAD = "vsp2"
