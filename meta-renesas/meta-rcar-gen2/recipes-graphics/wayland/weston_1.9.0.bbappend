EXTRA_OECONF += "--enable-gst-recorder "
DEPENDS += "media-ctl gstreamer1.0 gstreamer1.0-plugins-base libjpeg-turbo"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
 	       file://0001-Add-virtual-output-support.patch \
    	       file://0002-Get-DMA-fd-on-bo.patch \
	       file://0003-Add-gst-recorder-for-h264-output-streaming.patch \
	       file://0004-Fix-GST-error-complainings.patch \
	       file://0005-gst-record-switch-to-gettime-function-to-CLOCK_MONOT.patch \
               file://weston.service \
"
