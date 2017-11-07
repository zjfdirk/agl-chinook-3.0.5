require recipes-core/images/core-image-base.bb
require core-image-renesas-base.inc

DESCRIPTION = "Image with Qt5 support"

IMAGE_FEATURES += " \
    dev-pkgs tools-sdk \
    tools-debug debug-tweaks \
    ssh-server-openssh \
"

DEPENDS += " meta-toolchain-qt5"

IMAGE_INSTALL_append = " kernel-devsrc ltp packagegroup-qt5"
