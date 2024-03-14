# Copyright (C) 2009-2015, O.S. Systems Software Ltda. All Rights Reserved
# Released under the MIT license (see packages/COPYING)

include firefox_crates_stable.inc
include firefox.inc

LIC_FILES_CHKSUM = "file://toolkit/content/license.html;md5=b3bfdbd7d634f84723c5cce6c2c04ad2"

FILESEXTRAPATHS:prepend := "${THISDIR}/firefox-stable:"

SRC_URI += "git://github.com/franziskuskiefer/cose-rust;protocol=https;branch=master;name=cose-rust;destsuffix=cose-rust \
            git://github.com/rust-minidump/minidump-writer.git;protocol=https;branch=main;name=minidump-writer;destsuffix=minidump-writer \
            git://github.com/rust-minidump/rust-minidump;protocol=https;branch=main;name=minidump-common;destsuffix=minidump-common \
            git://github.com/hsivonen/packed_simd.git;protocol=https;branch=master;name=packed-simd;destsuffix=packed_simd \
            git://github.com/mozilla/mp4parse-rust;protocol=https;branch=master;name=mp4parse;destsuffix=mp4parse \
            git://github.com/gfx-rs/metal-rs;protocol=https;branch=master;name=metal;destsuffix=metal \
            git://github.com/seanmonstar/warp;protocol=https;branch=master;name=warp;destsuffix=warp \
            git://github.com/servo/rust-cssparser;protocol=https;branch=master;name=cssparser;destsuffix=cssparser"

SRC_URI[sha256sum] = "d5dcb955b65e0f164a90cac0760724486e36e896221b98f244801dfd045d741c"

SRCREV_FORMAT .= "_application-services"
SRCREV_application-services = "63a6260c14847c21c5a1fa3003efaf0114a3e4e5"
SRCREV_FORMAT .= "_cose-rust"
SRCREV_cose-rust = "43c22248d136c8b38fe42ea709d08da6355cf04b"
SRCREV_FORMAT .= "_minidump-writer"
SRCREV_minidump-writer = "99c561931fe8cf1fa2135b3f23ff6588bef8fd1e"
SRCREV_FORMAT .= "_minidump-common"
SRCREV_minidump-common = "c3de84b061339c686a572fb9f059e7ba3fad38d6"
SRCREV_FORMAT .= "_packed-simd"
SRCREV_packed-simd = "d938e39bee9bc5c222f5f2f2a0df9e53b5ce36ae"


SRCREV_FORMAT .= "_mp4parse"
SRCREV_mp4parse = "d262e40e7b80f949dcdb4db21caa6dbf1a8b2043"
SRCREV_FORMAT .= "_neqo"
SRCREV_neqo = "83735a88217a6b3a6a9d3cd5d9243040c5e41319"
SRCREV_FORMAT .= "_wgpu"
SRCREV_wgpu = "c6eea50b04127abe2340b93141123312baf5414b"
SRCREV_FORMAT .= "_naga"
SRCREV_naga = "92e41b43e437146b5d946eb238de963be1168016"
SRCREV_FORMAT .= "_uniffi-rs"
SRCREV_uniffi-rs = "afb29ebdc1d9edf15021b1c5332fc9f285bbe13b"
SRCREV_FORMAT .= "_metal"
SRCREV_metal = "f507da4686234e658f31de54d2aa0dfa8abd236b"

SRCREV_FORMAT .= "_cssparser"
SRCREV_cssparser = "aaa966d9d6ae70c4b8a62bb5e3a14c068bb7dff0"
SRCREV_FORMAT .= "_audioipc"
SRCREV_audioipc = "6be424d75f1367e70f2f5ddcacd6d0237e81a6a9"
SRCREV_FORMAT .= "_wpf-gpu-raster"
SRCREV_wpf-gpu-raster = "99979da091fd58fba8477e7fcdf5ec0727102916"

SRCREV_FORMAT .= "_warp"
SRCREV_warp = "9d081461ae1167eb321585ce424f4fef6cf0092b"
SRCREV_FORMAT .= "_cubeb-pulse"
SRCREV_cubeb-pulse = "c04c4d2c7f2291cb81a1c48f5a8c425748f18cd8"

SRCREV_FORMAT .="_midir"
SRCREV_midir = "85156e360a37d851734118104619f86bd18e94c6"

SRCREV_FORMAT .= "_cubeb-coreaudio"
SRCREV_cubeb-coreaudio = "89abc256a2eab3398f880e114b2d8308f5bc1d1a"
SRCREV_FORMAT .= "_aa-stroke"
SRCREV_aa-stroke = "ed4206ea11703580cd1d4fc63371a527b29d8252"
SRCREV_FORMAT .= "_jsparagus"
SRCREV_jsparagus = "61f399c53a641ebd3077c1f39f054f6d396a633c"

PACKAGECONFIG[x11-only] = "--enable-default-toolkit=cairo-gtk3-x11-only,,,,,wayland-only"
PACKAGECONFIG[wayland-only] = "--enable-default-toolkit=cairo-gtk3-wayland-only,,virtual/egl,,,x11-only"

do_configure:append(){
    wayland_only='${@bb.utils.contains("PACKAGECONFIG", "wayland-only", "true", "false", d)}'
    x11_only='${@bb.utils.contains("PACKAGECONFIG", "x11-only", "true", "false", d)}'

    if [ "$x11_only" = "true" ]; then
        sed -i '/ac_add_options --enable-default-toolkit=cairo-gtk3$/d' ${MOZCONFIG}
    fi

    if [ "$wayland_only" = "true" ]; then
        sed -i '/ac_add_options --enable-default-toolkit=cairo-gtk3-wayland$/d' ${MOZCONFIG}
    fi
}
