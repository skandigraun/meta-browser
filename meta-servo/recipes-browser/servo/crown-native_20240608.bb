SUMMARY = "Crown is the Rust linter used with the Servo browser"
HOMEPAGE = "https://servo.org"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7f365ee39768c228fc315b749d71ded6"

inherit cargo native

require servo-common.inc

DEPENDS += "cargo-native rust-native" 

# libgcc

S = "${WORKDIR}/git"

CARGO_BUILD_FLAGS = "--frozen --target ${RUST_BUILD_SYS}"

do_compile(){
    export RUSTC_BOOTSTRAP=crown
    cargo install --path ${S}/support/crown ${CARGO_BUILD_FLAGS}
}
