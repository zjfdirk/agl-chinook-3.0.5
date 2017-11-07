require ../../include/gles-control.inc

SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/gst-omx.git;protocol=git;branch=RCAR-GEN2/1.0.0"
SRCREV_rcar-gen2 = "${@'e0a23fb50ec211a8058eac223847bbcc574fb343' \
    if '1' in '${USE_GLES_WAYLAND}' else '05563465faad99243ee2dd30547e3075eb8cf5e3'}"

LIC_FILES_CHKSUM_remove_rcar-gen2 = " file://omx/gstomx.h;beginline=1;endline=21;md5=5c8e1fca32704488e76d2ba9ddfa935f"
LIC_FILES_CHKSUM_append_rcar-gen2 = " file://omx/gstomx.h;beginline=1;endline=22;md5=17e5f2943dace9e5cde4a8587a31e8f9"
S = "${WORKDIR}/git"

do_configure() {
    cd ${S}
    ./autogen.sh --noconfigure
    cd ${B}
    oe_runconf
}

DEPENDS_append_rcar-gen2 = " omx-user-module mmngrbuf-user-module"
EXTRA_OECONF_append_rcar-gen2 = " \
    --with-omx-target=rcar --enable-experimental \
    '${@'--enable-nv12-page-alignment' if '${USE_GLES_WAYLAND}' == '1' else ''}' \
    '${@'--disable-dmabuf' if '${USE_GLES}' == '0' and '${USE_WAYLAND}' == '1' else ''}'"

# Overwrite do_install[postfuncs] += " set_omx_core_name "
# because it will force the plugin to use bellagio instead of our config
revert_omx_core_name() {
    sed -i -e "s;^core-name=.*;core-name=/usr/local/lib/libomxr_core.so;" "${D}/etc/xdg/gstomx.conf"
}

REVERT_OMX_CORE_NAME = ""
REVERT_OMX_CORE_NAME_rcar-gen2 = "revert_omx_core_name"
do_install[postfuncs] += "${REVERT_OMX_CORE_NAME}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
	       file://0001-omx-videodec-add-planebuf-to-allocation-request.patch \
    	       file://0002-Fixed-memory-corruption-and-bad-access.patch \
	       file://0003-omxvideoenc-export-dmafd-buffer-through-own-buffer-p.patch \
	       file://0004-Export-a-first-dmabuf-file-descriptor-with-the-whole.patch \
	       file://0005-gssomxbufferpool-add-exported-flag.patch \
	       file://0006-gstomxbufferpool-create-dmabuf-for-input-port.patch \
	       file://0007-gstomxbufferpool-add-helper-to-get-omxbuffer-from-gs.patch \
	       file://0008-gstomxenc-do-not-allocate-output-buffers-two-times.patch \
	       file://0009-gstomxenc-move-encoder-disable-code-to-separate-func.patch \
	       file://0010-omxvideodec-support-creating-buffers-using-sink.patch \
	       file://gstomx.conf \
"
