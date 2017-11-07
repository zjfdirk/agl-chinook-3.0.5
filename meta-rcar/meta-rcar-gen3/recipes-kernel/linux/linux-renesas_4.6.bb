DESCRIPTION = "Linux kernel for the R-Car Generation 3 based board"

require include/avb-control.inc
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${MACHINE}:"
COMPATIBLE_MACHINE = "(salvator-x|h3ulcb|m3ulcb|ttardrive|eagle)"

RENESAS_BSP_URL = "git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-bsp.git"
BRANCH = "v4.6/rcar-3.3.x"
SRCREV = "f100fac1e2a41c8f0d52f7b5607472a5e5e7c010"

SRC_URI = "${RENESAS_BSP_URL};protocol=git;nocheckout=1;branch=${BRANCH}"

LINUX_VERSION ?= "4.6"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

SRC_URI_append = " \
    file://defconfig \
    file://touch.cfg \
    ${@base_conditional("USE_AVB", "1", " file://usb-video-class.cfg", "", d)} \
    file://0001-arm64-renesas-Add-H3ULCB-board.patch \
    file://0002-staging-boards-Add-H3ULCB-staging.patch \
    file://0003-spi-sh-msiof-fixes.patch \
    file://0004-spi-spidev-add-spi-gpio-into-spidev.patch \
    file://0005-spi-spi-gpio-fix-CPOL-mode.patch \
    file://0006-xhci-rcar-add-firmware-for-R-Car-H2-M2-USB-3.0-host-.patch \
    file://0007-usb-host-xhci-plat-add-support-for-the-R-Car-H3-xHCI.patch \
    file://0008-spi-spi-gpio-fix-set-CPOL-default-inverted.patch \
    file://0010-mmc-sh_mobile_sdhi-Add-R-CarGen3-SDHI-SEQUENCER-supp.patch \
    file://0011-arm64-renesas-Add-M3ULCB-board.patch \
    file://0012-staging-boards-Add-M3ULCB-staging.patch \
    file://0013-arm64-mm-only-initialize-swiotlb-when-necessary.patch \
    file://0014-Revert-PCI-rcar-pcie-Add-bus-notifier-so-we-can-limi.patch \
    file://0015-arm64-do-not-set-dma-masks-that-device-connection-ca.patch \
    file://0016-swiotlb-ensure-that-page-sized-mappings-are-page-ali.patch \
    file://0017-PCI-rcar-Add-multi-MSI-support.patch \
    file://0018-PCI-rcar-Try-increasing-PCIe-link-speed-to-5-GT-s-at.patch \
    file://0019-can-rcar_can-add-enable-and-standby-control-pins.patch \
    file://0020-can-rcar-canfd-Add-Renesas-R-Car-CAN-FD-driver.patch \
    file://0021-arm64-dts-r8a7795-Add-CAN-FD-support.patch \
    file://0022-can-rcar_canfd-use-explicit-clock_select-from-dts.patch \
    file://0023-can-rcar_canfd-add-enable-and-standby-control-pins.patch \
    file://0024-mtd-Add-RPC-HyperFlash-driver.patch \
    file://0025-IMR-driver-interim-patch.patch \
    file://0026-lib-swiotlb-reduce-verbosity.patch \
    file://0027-mm-introduce-dedicated-WQ_MEM_RECLAIM-workqueue-to-d.patch \
    file://0028-gpio-max732x-fix-gpio-set.patch \
    file://0029-gpio-gpiolib-suppress-gpiod-warning.patch \
    file://0030-arm64-renesas-r8a7797-Add-Renesas-R8A7797-SoC-suppor.patch \
    file://0031-arm64-dts-r8a7796-Add-CAN-FD-support.patch \
    file://0032-drm-adv7511-Enable-HPD-interrupts-to-support-hotplug.patch \
    file://0033-drm-adv7511-add-polling-mode-when-no-irq-available.patch \
    file://0034-usb-host-xhci-plat-add-support-for-Renesas-r8a7796-S.patch \
    file://0035-usb-host-xhci-rcar-add-a-new-firmware-version-for-r8.patch \
    file://0036-usb-host-xhci-plat-add-firmware-for-the-R-Car-M3-W-x.patch \
    file://0037-usb-host-xhci-rcar-update-firmware-for-R-Car-H3-and-.patch \
    file://0038-serial-sh-sci-Fix-panic-when-serial-console-and-DMA-.patch \
    file://0040-H3-MAX9286-TI964-support-add-10635-10640-cameras.patch \
    file://0041-media-i2c-Add-ov5647-sensor.patch \
    file://0042-media-i2c-Add-ov5642-sensor.patch \
    file://0050-arm64-renesas-Salvator-X-View-H3-board-support.patch \
    file://0051-arm64-renesas-H3ULCB-HAD-support.patch \
    file://0052-arm64-renesas-H3ULCB-View-board-support.patch \
    file://0053-arm64-renesas-Salvator-X-View-M3-board-support.patch \
    file://0054-arm64-renesas-M3ULCB-View-board-support.patch \
    file://0055-arm64-dts-r8a7795-h3ulcb-had-set-console-from-rdrive.patch \
    file://0056-arm64-dts-r8a7795-h3ulcb-had-route-RAVB-to-rdrive.patch \
    file://0057-arm64-renesas-H3ULCB-Kingfisher-board-support.patch \
    file://0058-arm64-renesas-M3ULCB-Kingfisher-board-support.patch \
    file://0059-arm64-renesas-TTA-R-Drive-board-support.patch \
    file://0060-arm64-renesas-V3M-Eagle-board-support.patch \
    file://0061-staging-boards-Add-V3M-Eagle-staging.patch \
    file://0062-arm64-renesas-H3ULCB-Videobox-board-support.patch \
    ${@base_conditional("LVDSCAMERA_FIRST4_TYPE1", "1", " file://0070-arm64-dts-Gen3-view-boards-TYPE1-first-4-cameras.patch", "", d)} \
    ${@base_conditional("LVDSCAMERA_FIRST4_TYPE2", "1", " file://0071-arm64-dts-Gen3-view-boards-TYPE2-first-4-cameras.patch", "", d)} \
    ${@base_conditional("LVDSCAMERA_SECOND4_TYPE1", "1", " file://0072-arm64-dts-Gen3-view-boards-TYPE1-second-4-cameras.patch", "", d)} \
    ${@base_conditional("RAVB_DEBUG", "1", " file://0080-net-ethernet-renesas-ravb-packets-dump.patch", "", d)} \
    file://0081-mic-vop-scif-rename-ioremap-and-iounmap-methods.patch \
    file://0082-mic-vop-abstract-allocation-of-peer-mappable-memory.patch \
    file://0083-mic-vop-abstract-VOP-device-DMA-setup.patch \
    file://0084-mic-vop-ensure-struct-_mic_vring_info-alignment.patch \
    file://0085-mic-vop-rework-mmap.patch \
    file://0086-mic-vop-reassign-used-ring-in-a-safe-way.patch \
    file://0087-virtio-allow-device-to-enforce-dma-mapping-of-data-b.patch \
    file://0088-mic-vop-allow-enforcement-of-dma-mapping-of-virtio-d.patch \
    file://0089-vringh-support-ring-structure-located-in-i-o-space.patch \
    file://0090-mic-vop-use-vringh_complete_kern_io.patch \
    file://0091-vringh-add-data-push-pull-with-external-copy-helper.patch \
    file://0092-mic-vop-use-vringh_iov_-push-pull-_-user-kern-_ext.patch \
    file://0093-mic-vop-refactor-data-transfer-routines.patch \
    file://0094-mic-vop-avoid-extra-rescheduling-on-each-interrupt.patch \
    file://0095-mic-mpssd-fix-logging.patch \
    file://0096-mic-mpssd-support-VOP-on-non-MIC.patch \
    file://0097-mic-vop-fix-crashes-on-access-to-VOP-debugfs-entries.patch \
    file://0098-rcar-add-virtio-over-pcie-interconnect-framework.patch \
    file://0099-ti-st-add-device-tree-support.patch \
    file://0100-btwilink-add-minimal-device-tree-support.patch \
    file://0101-ASoC-Modify-check-condition-of-multiple-bindings-of-.patch \
    file://0102-ASoC-add-dummy-Si468x-driver.patch \
    file://0103-wl18xx-do-not-invert-IRQ-on-WLxxxx-side.patch \
    file://0104-ASoC-R-Car-add-tdm16-support-enable-tdm-for-ssi78.patch \
    file://0105-IIO-lsm9ds0-add-IMU-driver.patch \
    file://0106-ASoC-PCM3168A-add-TDM-modes-merge-ADC-and-DAC.patch \
    file://0107-ADV7511-limit-maximum-pixelclock.patch \
"

SRC_URI_append_h3ulcb = " \
    file://h3ulcb.cfg \
"

SRC_URI_append_h3ulcb = '${@ \
    " file://hyperflash.cfg " if 'h3ulcb-had' in '${MACHINE_FEATURES}' else \
    ""}'

SRC_URI_append_salvator-x = " \
    file://salvator-x.cfg \
"

SRC_URI_append_m3ulcb = " \
    file://m3ulcb.cfg \
"

SRC_URI_append_ttardrive = " \
    file://ttardrive.cfg \
"

SRC_URI_append_eagle = " \
    file://eagle.cfg \
"

KERNEL_DEVICETREE_append_h3ulcb = " \
    renesas/r8a7795-h3ulcb-had-alfa.dtb renesas/r8a7795-h3ulcb-had-beta.dtb \
    renesas/r8a7795-h3ulcb-view.dtb \
    renesas/r8a7795-h3ulcb-kf.dtb renesas/r8a7795-h3ulcb-kf-v1.dtb \
    renesas/r8a7795-h3ulcb-vb.dtb \
"

KERNEL_DEVICETREE_append_salvator-x = " \
    renesas/r8a7795-salvator-x-view.dtb \
    renesas/r8a7796-salvator-x-view.dtb \
"

KERNEL_DEVICETREE_append_m3ulcb = " \
    renesas/r8a7796-m3ulcb-view.dtb \
    renesas/r8a7796-m3ulcb-kf.dtb renesas/r8a7796-m3ulcb-kf-v1.dtb \
"
