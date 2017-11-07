DESCRIPTION = "Package containing PDK makefile infrastructure"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://makerules/env.mk;beginline=1;endline=31;md5=10503e8de09f5475185f3fd56de1e310"

require recipes-ti/includes/ti-paths.inc

PV = "01.00.00.03"
PR = "r0"

PDK_BUILD_GIT_URI = "git://git.ti.com/keystone-rtos/processor-pdk-build.git"
PDK_BUILD_GIT_PROTOCOL = "git"
PDK_BUILD_GIT_BRANCH = "master"

# Below Commit ID corresponds to "DEV.PDK_BUILD.01.00.00.03"
PDK_BUILD_SRCREV = "487845b835b54d97f2d2f47ee1d0d88784e7e9ca"

BRANCH = "${PDK_BUILD_GIT_BRANCH}"
SRC_URI = "${PDK_BUILD_GIT_URI};protocol=${PDK_BUILD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${PDK_BUILD_SRCREV}"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone"

S = "${WORKDIR}/git/"

CLEANBROKEN = "1"

do_compile() {
    :
}

do_install() {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    
    install -m 0755 Rules.make ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build
    install -m 0755 comp_top.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build

    sed -i -e "s|c\:/ti/ccsv5/utils/cygwin|\$(XDC_INSTALL_PATH)/bin|g" \
        ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/Rules.make
    
    install -m 0755 makerules/build_config.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    install -m 0755 makerules/common.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    install -m 0755 makerules/component.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    install -m 0755 makerules/env.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    install -m 0755 makerules/platform.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
}

do_install_append_ti33x() {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am335x

    install -m 0755 makerules/rules_a8.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    install -m 0755 makerules/rules_a8_extend.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules

    install -m 0755 am335x/config_am335x_a8.bld ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am335x
}

do_install_append_ti43x() {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am437x

    install -m 0755 makerules/rules_a9.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    
    install -m 0755 am437x/config_am437x_a9.bld ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am437x
}

do_install_append_omap-a15() {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am571x
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am572x

    install -m 0755 makerules/rules_66.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    install -m 0755 makerules/rules_a15.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    install -m 0755 makerules/rules_m4.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    install -m 0755 makerules/rules_ti_cgt_arm.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    
    install -m 0755 am571x/config_am571x.bld ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am571x
    install -m 0755 am571x/config_am571x_c66.bld ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am571x
    install -m 0755 am571x/config_am571x_a15.bld ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am571x
    install -m 0755 am571x/mem_segment_definition_1024mb_bios.xs ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am571x
    install -m 0755 am572x/config_am572x.bld ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am572x
    install -m 0755 am572x/config_am572x_c66.bld ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am572x
    install -m 0755 am572x/config_am572x_a15.bld ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am572x
    install -m 0755 am572x/mem_segment_definition_1024mb_bios.xs ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/am572x
}
    
do_install_append_keystone() {
    install -m 0755 makerules/rules_66.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
    install -m 0755 makerules/rules_a15.mk ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/makerules
}

do_install_append_k2g-evm() {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/k2g

    install -m 0755 k2g/config_k2g_a15.bld ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/build/k2g
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages/*"
