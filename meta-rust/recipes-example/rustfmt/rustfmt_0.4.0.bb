inherit cargo

CARGO_INDEX_COMMIT = "3b3994e099281c394a6a66604d1af6c0920e4c31"

SRC_URI = " \
    crate://crates.io/aho-corasick/0.5.1 \
    crate://crates.io/bitflags/0.5.0 \
    crate://crates.io/diff/0.1.9 \
    crate://crates.io/env_logger/0.3.2 \
    crate://crates.io/getopts/0.2.14 \
    crate://crates.io/kernel32-sys/0.2.1 \
    crate://crates.io/libc/0.2.8 \
    crate://crates.io/log/0.3.5 \
    crate://crates.io/memchr/0.1.10 \
    crate://crates.io/regex/0.1.58 \
    crate://crates.io/regex-syntax/0.3.0 \
    crate://crates.io/rustc-serialize/0.3.18 \
    crate://crates.io/strings/0.0.1 \
    crate://crates.io/syntex_syntax/0.30.0 \
    crate://crates.io/term/0.2.14 \
    crate://crates.io/toml/0.1.28 \
    crate://crates.io/unicode-segmentation/0.1.2 \
    crate://crates.io/unicode-xid/0.0.3 \
    crate://crates.io/utf8-ranges/0.1.3 \
    crate://crates.io/winapi/0.2.6 \
    crate://crates.io/winapi-build/0.1.1 \
    crate://crates.io/rustfmt/0.4.0 \
    crate-index://crates.io/${CARGO_INDEX_COMMIT} \
"
SRC_URI[md5sum] = "7fc46357c9c5e72a3a1ec3630c8c7a05"
SRC_URI[sha256sum] = "770c66dc845424a0c9a7f51b47d8de1e2605298da9b257ddde1d5be6fe01331f"
SRC_URI[index.md5sum] = "79f10f436dbf26737cc80445746f16b4"
SRC_URI[index.sha256sum] = "86114b93f1f51aaf0aec3af0751d214b351f4ff9839ba031315c1b19dcbb1913"

# rustfmt 0.5.0
#LIC_FILES_CHKSUM=" \
#    file://LICENSE-APACHE;md5=1836efb2eb779966696f473ee8540542 \
#    file://LICENSE-MIT;md5=0b29d505d9225d1f0815cbdcf602b901 \
#"
LIC_FILES_CHKSUM="file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SUMMARY = "Format Rust Code"
HOMEPAGE = "https://github.com/rust-lang-nursery/rustfmt"
LICENSE = "MIT | Apache-2.0"
