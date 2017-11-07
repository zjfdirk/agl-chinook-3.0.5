require linux.inc
require recipes-kernel/linux/linux-dtb.inc
require linux-dtb-append.inc

DESCRIPTION = "Linux kernel for the R-Car Generation 2 based board"
COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager|porter|silk)"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PV_append = "+git${SRCREV}"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "165e12ce2d7839e755debbec78dfa43b54345275"
SRC_URI = "${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.9.7 \
	file://0001-kernel-add-support-for-gcc-5.patch \
	file://0001-arm-lager-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-koelsch-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-alt-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-arm-gose-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
	file://0001-ASoC-ak4642-Replace-mdelay-function-to-msleep.patch \
"

SRC_URI_append_porter = " \
	file://0001-kernel-Silk-board-support.patch \
	file://0002-silk-reference-Add-DRM_RCAR_DU_CONNECT_VSP-configura.patch \
	file://0002-kernel-silk-fix-ethernet-phy-irq.patch \
	file://0003-kernel-silk-fix-sd-detect.patch \
	file://0004-kernel-Revert-i2c-rcar-Support-ACK-by-HW-auto-restart-after-NACK.patch \
	file://0006-Rcar-DU-add-RGB-connector.patch \
	file://0007-SILK-add-i2c0.patch \
	file://0008-Porter-board-support.patch \
	file://0009-porter-reference-Add-DRM_RCAR_DU_CONNECT_VSP-configu.patch \
	file://0009-shmobile-add-atag-dtb-compat.patch \
	file://0010-Silk-Add-missing-pins-handle-to-Eth.patch \
	file://0011-Silk-Add-missing-DU-pins.patch \
	file://0012-can-add-Renesas-R-Car-CAN-driver.patch \
	file://0013-sh-pfc-r8a7791-add-CAN-pin-groups.patch \
	file://0014-sh-pfc-r8a7791-fix-CAN-pin-groups.patch \
	file://0015-can-rcar_can-support-all-input-clocks.patch \
	file://0016-can-rcar_can-document-device-tree-bindings.patch \
	file://0017-can-rcar_can-add-device-tree-support.patch \
	file://0018-porter-can-support.patch \
	file://0019-i2c-busses-rcar-Workaround-arbitration-loss-error.patch \
	file://0020-Silk-Remove-I2C1-clock-from-clk_enables.patch \
	file://0001-ARM-shmobile-porter-board-Remove-Audio-platform-code.patch \
	file://0002-ARM-shmobile-porter-Sound-PIO-support-on-DTS.patch \
	file://0003-ARM-shmobile-porter-Sound-DMA-support-on-DTS.patch \
	file://0004-ARM-shmobile-porter-Sound-DMA-support-via-BUSIF-on-D.patch \
	file://0005-ARM-shmobile-porter-Sound-DMA-support-via-SRC-on-DTS.patch \
	file://0006-ARM-shmobile-porter-Sound-DMA-support-via-DVC-on-DTS.patch \
	file://0007-ARM-shmobile-porter-Fix-audio-clk-master-to-SSI-and-.patch \
	file://0008-drm-rcar-du-r8a7791-disable-interlace-mode.patch \
        file://0022-fanotify-fix-notification-of-groups-with-inode-mount.patch \
"

SRC_URI_append_silk = " \
	file://0001-kernel-Silk-board-support.patch \
	file://0002-silk-reference-Add-DRM_RCAR_DU_CONNECT_VSP-configura.patch \
	file://0002-kernel-silk-fix-ethernet-phy-irq.patch \
	file://0003-kernel-silk-fix-sd-detect.patch \
	file://0004-kernel-Revert-i2c-rcar-Support-ACK-by-HW-auto-restart-after-NACK.patch \
	file://0006-Rcar-DU-add-RGB-connector.patch \
	file://0007-SILK-add-i2c0.patch \
	file://0008-Porter-board-support.patch \
	file://0009-porter-reference-Add-DRM_RCAR_DU_CONNECT_VSP-configu.patch \
	file://0009-shmobile-add-atag-dtb-compat.patch \
	file://0010-Silk-Add-missing-pins-handle-to-Eth.patch \
	file://0011-Silk-Add-missing-DU-pins.patch \
	file://0012-can-add-Renesas-R-Car-CAN-driver.patch \
	file://0013-sh-pfc-r8a7791-add-CAN-pin-groups.patch \
	file://0014-sh-pfc-r8a7791-fix-CAN-pin-groups.patch \
	file://0015-can-rcar_can-support-all-input-clocks.patch \
	file://0016-can-rcar_can-document-device-tree-bindings.patch \
	file://0017-can-rcar_can-add-device-tree-support.patch \
	file://0018-porter-can-support.patch \
	file://0019-i2c-busses-rcar-Workaround-arbitration-loss-error.patch \
	file://0020-Silk-Remove-I2C1-clock-from-clk_enables.patch \
	file://0021-drm-rcar-du-r8a7794-disable-interlace-mode.patch \
"

# smack patches are applied if DISTRO_FEATURES has "smack"
# smack patches from 3.10-rc1 to 3.14
SRC_URI_append_smack = " \
	file://smack/0001-Smack-Local-IPv6-port-based-controls.patch \
	file://smack/0002-Smack-Improve-access-check-performance.patch \
	file://smack/0003-Smack-Add-smkfstransmute-mount-option.patch \
	file://smack/0004-Smack-Fix-possible-NULL-pointer-dereference-at-smk_n.patch \
	file://smack/0005-Smack-Fix-the-bug-smackcipso-can-t-set-CIPSO-correct.patch \
	file://smack/0006-Security-Add-Hook-to-test-if-the-particular-xattr-is.patch \
	file://smack/0007-xattr-Constify-name-member-of-struct-xattr.patch \
	file://smack/0008-security-smack-fix-memleak-in-smk_write_rules_list.patch \
	file://smack/0009-security-smack-add-a-hash-table-to-quicken-smk_find_.patch \
	file://smack/0010-Smack-network-label-match-fix.patch \
	file://smack/0011-Smack-IPv6-casting-error-fix-for-3.11.patch \
	file://smack/0012-Smack-parse-multiple-rules-per-write-to-load2-up-to-.patch \
	file://smack/0013-Smack-Implement-lock-security-mode.patch \
	file://smack/0014-Smack-Ptrace-access-check-mode.patch \
	file://smack/0015-smack-fix-allow-either-entry-be-missing-on-access-ac.patch \
	file://smack/0016-Smack-Prevent-the-and-labels-from-being-used-in-SMAC.patch \
	file://smack/0017-Smack-Make-the-syslog-control-configurable.patch \
	file://smack/0018-Smack-change-rule-cap-check.patch \
	file://smack/0019-Smack-Rationalize-mount-restrictions.patch \
	file://smack/0020-Smack-File-receive-audit-correction.patch \
	file://smack/0021-smack-call-WARN_ONCE-instead-of-calling-audit_log_st.patch \
"

# smack patches are applied if DISTRO_FEATURES has "smack"
# smack patches from 3.14 to 3.19
SRC_URI_append_smack = " \
	file://smack/0022-smack-fix-key-permission-verification.patch \
	file://smack/0023-Minor-improvement-of-smack_sb_kern_mount.patch \
	file://smack/0024-Smack-fix-the-subject-object-order-in-smack_ptrace_t.patch \
	file://smack/0025-Smack-unify-all-ptrace-accesses-in-the-smack.patch \
	file://smack/0026-Smack-adds-smackfs-ptrace-interface.patch \
	file://smack/0027-bugfix-patch-for-SMACK.patch \
	file://smack/0028-SMACK-Fix-handling-value-NULL-in-post-setxattr.patch \
	file://smack/0029-Smack-Correctly-remove-SMACK64TRANSMUTE-attribute.patch \
	file://smack/0030-Smack-bidirectional-UDS-connect-check.patch \
	file://smack/0031-Smack-Verify-read-access-on-file-open-v3.patch \
	file://smack/0032-Smack-Label-cgroup-files-for-systemd.patch \
	file://smack/0033-Warning-in-scanf-string-typing.patch \
	file://smack/0034-Smack-fix-behavior-of-smack_inode_listsecurity.patch \
	file://smack/0035-Smack-handle-zero-length-security-labels-without-pan.patch \
	file://smack/0036-Smack-remove-unneeded-NULL-termination-from-securtit.patch \
	file://smack/0037-Smack-Fix-setting-label-on-successful-file-open.patch \
	file://smack/0038-Smack-Bring-up-access-mode.patch \
	file://smack/0039-Small-fixes-in-comments-describing-function-paramete.patch \
	file://smack/0040-Fix-a-bidirectional-UDS-connect-check-typo.patch \
	file://smack/0041-Make-Smack-operate-on-smack_known-struct-where-it-st.patch \
	file://smack/0042-Smack-Lock-mode-for-the-floor-and-hat-labels.patch \
	file://smack/0043-Security-smack-replace-kzalloc-with-kmem_cache-for-i.patch \
	file://smack/0044-security-smack-fix-out-of-bounds-access-in-smk_parse.patch \
"

# smack patches are applied if DISTRO_FEATURES has "smack"
# smack patches from 3.19 to 4.0
SRC_URI_append_smack = " \
	file://smack/0045-smack-miscellaneous-small-fixes-in-function-comments.patch \
	file://smack/0046-smack-fix-logic-in-smack_inode_init_security-functio.patch \
	file://smack/0047-smack-introduce-a-special-case-for-tmpfs-in-smack_d_.patch \
	file://smack/0048-smack-Fix-a-bidirectional-UDS-connect-check-typo.patch \
	file://smack/0049-Smack-Rework-file-hooks.patch \
	file://smack/0050-Smack-secmark-support-for-netfilter.patch \
	file://smack/0051-smack-Add-missing-logging-in-bidirectional-UDS-conne.patch \
	file://smack/0052-smack-fix-possible-use-after-frees-in-task_security-.patch \
	file://smack/0053-Smack-Repair-netfilter-dependency.patch \
	file://smack/0054-Smack-secmark-connections.patch \
"

# smack patches are applied if DISTRO_FEATURES has "smack"
# smack patches for handling bluetooth
SRC_URI_append_smack = " \
	file://smack/0055-Smack-Assign-smack_known_web-as-default-smk_in-label.patch \
	file://smack/0056-Smack-Assign-smack_known_web-label-for-kernel-thread.patch \
	file://smack/0057-Smack-handles-socket-in-file_receive.patch \
"

SRC_URI_append_porter = " file://can-rcar.cfg"

# Backport fix for CVE-2016-5195
SRC_URI_append = " file://9999-Backport-fix-for-CVE-2016-5195.patch "

# Backport fix for CVE-2017-1000364
SRC_URI_append = " file://0001-SEC-Backport-mm-larger-stack-guard-gap-between-vmas.patch"
SRC_URI_append = " file://0002-SEC-Backport-mm-fix-new-crash-in-unmapped_area_topdo.patch"
SRC_URI_append = " file://0003-SEC-Backport-Allow-stack-to-grow-up-to-address-space.patch"


S = "${WORKDIR}/git"

KERNEL_DEFCONFIG = "shmobile_defconfig"

do_configure_prepend() {
        install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

do_install_append(){
    # modprobe automatically at boot
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}/${sysconfdir}/modules-load.d
        touch ${D}/${sysconfdir}/modules-load.d/cluster-demo.conf
        echo "mmngr"    >>  ${D}/${sysconfdir}/modules-load.d/cluster-demo.conf
        echo "mmngrbuf" >>  ${D}/${sysconfdir}/modules-load.d/cluster-demo.conf
        echo "uvcs_cmn" >>  ${D}/${sysconfdir}/modules-load.d/cluster-demo.conf
        echo "s3ctl"    >>  ${D}/${sysconfdir}/modules-load.d/cluster-demo.conf
        echo "vspm"     >>  ${D}/${sysconfdir}/modules-load.d/cluster-demo.conf
        echo "fdpm"     >>  ${D}/${sysconfdir}/modules-load.d/cluster-demo.conf
    fi
}

FILES_kernel-modules += " \
    ${sysconfdir}/modules-load.d/cluster-demo.conf \
    "
