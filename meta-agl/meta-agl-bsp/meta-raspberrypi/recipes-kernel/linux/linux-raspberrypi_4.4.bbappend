FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}-4.4:"

LINUX_VERSION = "4.4.16"

SRCREV = "26550dcfb86b0308a99f726abbfb55abb1b0f78c"

SRC_URI_append = "\
	${@base_conditional('USE_FAYTECH_MONITOR', '1', 'file://0002-faytech-fix-rpi.patch', '', d)} \
"

#-------------------------------------------------------------------------
# smack patches for handling bluetooth

SRC_URI_append_smack = "\
       file://0001-Smack-File-receive-for-sockets.patch \
       file://0002-smack-fix-cache-of-access-labels.patch \
       file://0003-Smack-ignore-null-signal-in-smack_task_kill.patch \
       file://0004-Smack-Assign-smack_known_web-label-for-kernel-thread.patch \
"

#-------------------------------------------------------------------------
# Fix CVE-2017-1000364
SRC_URI_append = " \
      file://0001-mm-larger-stack-guard-gap-between-vmas.patch \
      file://0002-Allow-stack-to-grow-up-to-address-space-limit.patch \
      file://0003-mm-fix-new-crash-in-unmapped_area_topdown.patch \
"


do_configure_append_smack() {
    # SMACK and Co
    kernel_configure_variable IP_NF_SECURITY m
    kernel_configure_variable IP6_NF_SECURITY m
    kernel_configure_variable EXT2_FS_SECURITY y
    kernel_configure_variable EXT3_FS_SECURITY y
    kernel_configure_variable EXT4_FS_SECURITY y
    kernel_configure_variable SECURITY y
    kernel_configure_variable SECURITY_SMACK y
    kernel_configure_variable TMPFS_XATTR y
    kernel_configure_variable DEFAULT_SECURITY "smack"
    kernel_configure_variable DEFAULT_SECURITY_SMACK y
    kernel_configure_variable FANOTIFY_ACCESS_PERMISSIONS y
}

do_configure_append_netboot() {
    # NBD for netboot
    kernel_configure_variable BLK_DEV_NBD y
    # ramblk for inird
    kernel_configure_variable BLK_DEV_RAM y
}

do_configure_append_sota() {
    # ramblk for inird
    kernel_configure_variable BLK_DEV_RAM y
}

do_configure_append() {

    # VC4 Wayland/Weston
    kernel_configure_variable I2C_BCM2835 y
    kernel_configure_variable DRM y
    kernel_configure_variable DRM_PANEL_RASPBERRYPI_TOUCHSCREEN y
    kernel_configure_variable DRM_VC4 y
    kernel_configure_variable FB_BCM2708 n

    # Enable support for TP-Link TL-W722N USB Wifi adapter
    kernel_configure_variable CONFIG_ATH_CARDS m
    kernel_configure_variable CONFIG_ATH9K_HTC m

    # Enable support for RTLSDR
    kernel_configure_variable CONFIG_MEDIA_USB_SUPPORT y
    kernel_configure_variable CONFIG_MEDIA_DIGITAL_TV_SUPPORT y
    kernel_configure_variable CONFIG_DVB_USB_V2 m
    kernel_configure_variable CONFIG_DVB_USB_RTL28XXU m

    # KEEP until fixed upstream:
      # Keep this the last line
      # Remove all modified configs and add the rest to .config
      sed -e "${CONF_SED_SCRIPT}" < '${WORKDIR}/defconfig' >> '${B}/.config'

      yes '' | oe_runmake oldconfig
      kernel_do_configure
}

CMDLINE_DEBUG = ""
CMDLINE_append = " usbhid.mousepoll=0"

# Add options to allow CMA to operate
CMDLINE_append = ' ${@base_conditional("ENABLE_CMA", "1", "coherent_pool=6M smsc95xx.turbo_mode=N", "", d)}'

KERNEL_MODULE_AUTOLOAD += "snd-bcm2835"
KERNEL_MODULE_AUTOLOAD += "hid-multitouch"

RDEPENDS_${PN} += "kernel-module-snd-bcm2835"
PACKAGES += "kernel-module-snd-bcm2835"
