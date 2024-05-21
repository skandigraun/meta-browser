DEPENDS += "libxkbcommon gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-ugly \
           gstreamer1.0-plugins-bad python3 libtool freetype libxi libunwind libxrandr libx11 gperf \
           fontconfig expat libxcursor libxmu dbus vulkan-loader libjpeg-turbo zlib harfbuzz ncurses \
           taplo-cli-native python3-toml python3-notify-py python3-mozlog \
           python3-mozterm python3-flask python3-werkzeug python3-blinker python3-itsdangerous \
           python3-mozprocess python3-colorama python3-mozinfo python3-pygithub python3-aiohttp python3-multidict \
           python3-yarl python3-aiosignal python3-frozenlist python3-loguru python3-six python3-markupsafe \
           python3-click python3-jinja2 python3-requests python3-urllib3 python3-charset-normalizer python3-idna \
           python3-certifi python3-distro python3-deprecated python3-wrapt python3-pyjwt python3-pynacl-native \
           rust-llvm-native linux-libc-headers pkgconfig-native libgit2 libcxx"

include servo-crates.inc

SRC_URI += "git://git@github.com/servo/servo.git;branch=main;protocol=https \
            git://git@github.com/servo/media;branch=main;protocol=https;name=media;destsuffix=media \
            git://git@github.com/servo/stylo.git;branch=2024-04-16;protocol=https;name=stylo;destsuffix=stylo \
            git://git@github.com/servo/rust-cssparser.git;branch=main;protocol=https;name=cssparser;destsuffix=cssparser \
            git://git@github.com/servo/webrender.git;branch=0.64;protocol=https;name=webrender;destsuffix=webrender \
            git://git@github.com/servo/webxr;branch=main;name=webxr;destsuffix=webxr;protocol=https \
            git://git@github.com/servo/fontsan.git;branch=main;destsuffix=fontsan;name=fontsan;protocol=https \
            git://git@github.com/pcwalton/signpost.git;branch=master;destsuffix=signpost;name=signpost;protocol=https \
            git://git@github.com/gfx-rs/wgpu;branch=trunk;name=wgpu;destsuffix=wgpu;protocol=https \
            git://git@github.com/servo/mozjs.git;name=mozjs;destsuffix=mozjs;branch=main;protocol=https \
            git://git@github.com/servo/rust-cssparser.git;name=cssparser;destsuffix=cssparser;branch=main;protocol=https \
            git://git@github.com/servo/webrender.git;name=webrender;destsuffix=webrender;protocol=https;branch=0.64 \
            file://0001-Don-t-use-virtual-env-for-building.patch \
            file://0001-don-t-try-to-download-rust-toolchain.patch \
            file://0001-use-offline-crates.patch \
            file://0001-Build-mozangle-with-TARGET_CXXFLAGS.patch;patchdir=${WORKDIR}/cargo_home \
            file://0001-Use-TARGET_CXXFLAGS-to-build-mozjs.patch;patchdir=${WORKDIR}/mozjs"

SRCREV = "5b13604bd8dbbd26275243d2882761b158291647"

SRCREV_FORMAT .= "_media"
SRCREV_media = "45756bef67037ade0f4f0125d579fdc3f3d457c8"

SRCREV_FORMAT .= "_stylo"
SRCREV_stylo = "ac65c5a27c1b9faf9e7bb5bbcb3a4837a810ef6b"

SRCREV_FORMAT .= "_webxr"
SRCREV_webxr = "88fd368d1cc110db0f6f5000de2e22a14c9423b5"

SRCREV_FORMAT .= "_fontsan"
SRCREV_fontsan = "8fbc406506cfd1f8ab60e625d1e926a0e72e1d8a"

SRCREV_FORMAT .= "_signpost"
SRCREV_signpost = "7ed712507f343c38646b9d1fefd049166f9c9a18"

SRCREV_FORMAT .= "_wgpu"
SRCREV_wgpu = "d0a5e48aa7e84683114c3870051cc414ae92ac03"

SRCREV_FORMAT .= "_mozjs"
SRCREV_mozjs = "89121ff072638faee148e418a3eb2c3a2229fde5"

SRCREV_FORMAT .= "_cssparser"
SRCREV_cssparser = "aaa966d9d6ae70c4b8a62bb5e3a14c068bb7dff0"

SRCREV_FORMAT .= "_webrender"
SRCREV_webrender = "9d354adf8955b1390dd56db89e6d5a9ea7880391"

do_compile:prepend(){
    # Remove duplications from the config. Why does this happen at all?

    sort ${S}/../cargo_home/config | uniq -c | sort | grep "^\s*2" && {

        head -n 42 ${S}/../cargo_home/config >> ${S}/../cargo_home/config.tmp
        tail -n 19 ${S}/../cargo_home/config >> ${S}/../cargo_home/config.tmp
        mv ${S}/../cargo_home/config.tmp ${S}/../cargo_home/config

        head -n 45 ${S}/../cargo_home/config >> ${S}/../cargo_home/config.tmp
        tail -n 15 ${S}/../cargo_home/config >> ${S}/../cargo_home/config.tmp
        mv ${S}/../cargo_home/config.tmp ${S}/../cargo_home/config

    } || true

    grep -v stylo ${S}/../cargo_home/config >> ${S}/../cargo_home/config.tmp
    mv ${S}/../cargo_home/config.tmp ${S}/../cargo_home/config

    grep -v mozjs ${S}/../cargo_home/config >> ${S}/../cargo_home/config.tmp
    mv ${S}/../cargo_home/config.tmp ${S}/../cargo_home/config

    grep -v wgpu ${S}/../cargo_home/config >> ${S}/../cargo_home/config.tmp
    mv ${S}/../cargo_home/config.tmp ${S}/../cargo_home/config

    grep -v webrender ${S}/../cargo_home/config >> ${S}/../cargo_home/config.tmp
    mv ${S}/../cargo_home/config.tmp ${S}/../cargo_home/config


    grep -v media ${S}/../cargo_home/config >> ${S}/../cargo_home/config.tmp
    mv ${S}/../cargo_home/config.tmp ${S}/../cargo_home/config

    grep -v webxr ${S}/../cargo_home/config >> ${S}/../cargo_home/config.tmp
    mv ${S}/../cargo_home/config.tmp ${S}/../cargo_home/config

    grep -v cssparser ${S}/../cargo_home/config >> ${S}/../cargo_home/config.tmp
    mv ${S}/../cargo_home/config.tmp ${S}/../cargo_home/config
}
