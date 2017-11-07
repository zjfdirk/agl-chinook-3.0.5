require fftw.inc

# conflicts with fftw and fftwl
EXCLUDE_FROM_WORLD = "1"

EXTRA_OECONF += "--enable-single \
    ${@bb.utils.contains('TUNE_FEATURES', 'neon', '--enable-neon', '', d)} \
"

SRC_URI[md5sum] = "2edab8c06b24feeb3b82bbb3ebf3e7b3"
SRC_URI[sha256sum] = "8f0cde90929bc05587c3368d2f15cd0530a60b8a9912a8e2979a72dbe5af0982"
