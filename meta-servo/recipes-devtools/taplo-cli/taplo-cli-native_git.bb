SUMMARY = "A TOML toolkit written in Rust"
HOMEPAGE = "https://taplo.tamasfe.dev/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=031446b925e893c029e5eeaa45abe2d5"

include taplo-cli-crates.inc

inherit cargo cargo-update-recipe-crates native

SRC_URI += "git://git@github.com/tamasfe/taplo.git;protocol=https;branch=master"

PV = "1.0+git"
SRCREV = "f7c5279af987c2ef02c8931618295bb3e5b52dec"

S = "${WORKDIR}/git"
