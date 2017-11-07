SUMMARY = "GStreamer 1.0 multimedia framework"
DESCRIPTION = "GStreamer is a multimedia framework for encoding and decoding video and sound. \
It supports a wide range of formats including mp3, ogg, avi, mpeg and quicktime."
HOMEPAGE = "http://gstreamer.freedesktop.org/"
BUGTRACKER = "https://bugzilla.gnome.org/enter_bug.cgi?product=Gstreamer"
SECTION = "multimedia"
LICENSE = "LGPLv2+"
DEPENDS = "glib-2.0 libxml2 bison-native flex-native"

inherit autotools pkgconfig gettext gobject-introspection 

PACKAGECONFIG[debug] = "--enable-debug,--disable-debug"
PACKAGECONFIG[check] = "--enable-check,--disable-check"

EXTRA_OECONF = "--disable-docbook --disable-gtk-doc \
                --disable-dependency-tracking \
                --disable-examples --disable-tests \
                --disable-valgrind \
                "

acpaths = "-I ${S}/common/m4 -I ${S}/m4"

delete_pkg_m4_file() {
        # This m4 file is out of date and is missing PKG_CONFIG_SYSROOT_PATH tweaks which we need for introspection
        rm -f "${S}/common/m4/pkg.m4" || true
        # sed the §%"!& hardcoded libtool
        cd ${S}
        for i in gst/Makefile.in gst/Makefile.am libs/gst/controller/Makefile.in libs/gst/controller/Makefile.am libs/gst/base/Makefile.in libs/gst/base/Makefile.am \
                 libs/gst/check/Makefile.in libs/gst/check/Makefile.am libs/gst/net/Makefile.in libs/gst/net/Makefile.am ; do
            sed -i -e 's#--libtool=\"\$(top_builddir)/libtool\"#--libtool=\"\$(LIBTOOL)\"#g' $i
        done
        cd -

}

do_configure[prefuncs] += " delete_pkg_m4_file"

do_install_prepend() {
        export GIR_EXTRA_LIBS_PATH="${B}/gst/.libs:${B}/libs/gst/base/.libs"
}

do_compile_prepend() {
        export GIR_EXTRA_LIBS_PATH="${B}/gst/.libs:${B}/libs/gst/base/.libs"
}

RRECOMMENDS_${PN}_qemux86    += "kernel-module-snd-ens1370 kernel-module-snd-rawmidi"
RRECOMMENDS_${PN}_qemux86-64 += "kernel-module-snd-ens1370 kernel-module-snd-rawmidi"

CACHED_CONFIGUREVARS += "ac_cv_header_valgrind_valgrind_h=no"

FILES_${PN} += " ${libdir}/gstreamer-1.0/*.so"
FILES_${PN}-dev += " ${libdir}/gstreamer-1.0/*.la ${libdir}/gstreamer-1.0/*.a"
FILES_${PN}-dbg += " ${libdir}/gstreamer-1.0/.debug/ ${libexecdir}/gstreamer-1.0/.debug/"

