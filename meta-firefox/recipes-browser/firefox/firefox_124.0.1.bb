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
            git://github.com/servo/rust-cssparser;protocol=https;branch=master;name=cssparser;destsuffix=cssparser \
            git://github.com/glandium/mio;protocol=https;branch=windows-sys;name=mio;destsuffix=mio \
            git://github.com/cloudflare/quiche;protocol=https;branch=master;name=quiche;destsuffix=quiche"

SRC_URI[sha256sum] = "ddac16aea855e057ff6be3c143f7155cc20f452e1f45eb6288ff27e9346ab843"

SRCREV_FORMAT .= "_application-services"
SRCREV_application-services = "5fc8ee2f0f6950e36d4096983757bd046d55df9f"
SRCREV_FORMAT .= "_cose-rust"
SRCREV_cose-rust = "43c22248d136c8b38fe42ea709d08da6355cf04b"
SRCREV_FORMAT .= "_minidump-writer"
SRCREV_minidump-writer = "99c561931fe8cf1fa2135b3f23ff6588bef8fd1e"
SRCREV_FORMAT .= "_minidump-common"
SRCREV_minidump-common = "c3de84b061339c686a572fb9f059e7ba3fad38d6"
SRCREV_FORMAT .= "_packed-simd"
SRCREV_packed-simd = "d938e39bee9bc5c222f5f2f2a0df9e53b5ce36ae"
SRCREV_FORMAT .= "_mio"
SRCREV_mio = "9a2ef335c366044ffe73b1c4acabe50a1daefe05"
SRCREV_FORMAT .= "_quiche"
SRCREV_quiche = "09ea4b244096a013071cfe2175bbf2945fb7f8d1"

SRCREV_FORMAT .= "_mp4parse"
SRCREV_mp4parse = "a138e40ec1c603615873e524b5b22e11c0ec4820"
SRCREV_FORMAT .= "_neqo"
SRCREV_neqo = "9489511f7c82786f55bc9c713cddbff825507ed7"
SRCREV_FORMAT .= "_wgpu"
SRCREV_wgpu = "07e59eb6fc7de3f682f1c401b9cf9f0da9ee4b4a"
SRCREV_FORMAT .= "_uniffi-rs"
SRCREV_uniffi-rs = "afb29ebdc1d9edf15021b1c5332fc9f285bbe13b"
SRCREV_FORMAT .= "_metal"
SRCREV_metal = "f507da4686234e658f31de54d2aa0dfa8abd236b"

SRCREV_FORMAT .= "_cssparser"
SRCREV_cssparser = "aaa966d9d6ae70c4b8a62bb5e3a14c068bb7dff0"
SRCREV_FORMAT .= "_audioipc"
SRCREV_audioipc = "596bdb7fbb5745ea415726e16bd497e6c850a540"
SRCREV_FORMAT .= "_wpf-gpu-raster"
SRCREV_wpf-gpu-raster = "99979da091fd58fba8477e7fcdf5ec0727102916"

SRCREV_FORMAT .= "_warp"
SRCREV_warp = "9d081461ae1167eb321585ce424f4fef6cf0092b"
SRCREV_FORMAT .= "_cubeb-pulse"
SRCREV_cubeb-pulse = "8ff972c8e2ec1782ff262ac4071c0415e69b1367"

SRCREV_FORMAT .="_midir"
SRCREV_midir = "85156e360a37d851734118104619f86bd18e94c6"

SRCREV_FORMAT .= "_cubeb-coreaudio"
SRCREV_cubeb-coreaudio = "d23ab55eab684b46f46e1da177c8814f6103a009"
SRCREV_FORMAT .= "_aa-stroke"
SRCREV_aa-stroke = "96e66f91bb8e8efb80ff144eabd668002aa89650"
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
