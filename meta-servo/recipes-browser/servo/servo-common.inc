DEPENDS += "python3-toml python3-notify-py python3-mozlog \
           python3-mozterm python3-flask python3-werkzeug python3-blinker python3-itsdangerous \
           python3-mozprocess python3-colorama python3-mozinfo python3-pygithub python3-aiohttp python3-multidict \
           python3-yarl python3-aiosignal python3-frozenlist python3-loguru python3-six python3-markupsafe \
           python3-click python3-jinja2 python3-requests python3-urllib3 python3-charset-normalizer python3-idna \
           python3-certifi python3-distro python3-deprecated python3-wrapt python3-pyjwt python3-pynacl-native \
           rust-llvm-native"

include servo-crates.inc

SRC_URI += "git://git@github.com/servo/servo.git;branch=main;protocol=https \
            git://git@github.com/servo/media;branch=main;protocol=https;name=media;destsuffix=media \
            git://git@github.com/servo/stylo.git;branch=2024-05-15;protocol=https;name=stylo;destsuffix=stylo \
            git://git@github.com/servo/webrender.git;branch=0.64;protocol=https;name=webrender;destsuffix=webrender \
            git://git@github.com/servo/webxr;branch=main;name=webxr;destsuffix=webxr;protocol=https \
            git://git@github.com/servo/fontsan.git;branch=main;destsuffix=fontsan;name=fontsan;protocol=https \
            git://git@github.com/pcwalton/signpost.git;branch=master;destsuffix=signpost;name=signpost;protocol=https \
            git://git@github.com/gfx-rs/wgpu;branch=trunk;name=wgpu;destsuffix=wgpu;protocol=https \
            git://git@github.com/servo/mozjs.git;name=mozjs;destsuffix=mozjs;branch=main;protocol=https \
            file://0001-Don-t-use-virtual-env-for-building.patch \
            file://0001-don-t-try-to-download-rust-toolchain.patch \
            file://0001-use-offline-crates.patch \
            file://0001-Build-mozangle-with-TARGET_CXXFLAGS.patch;patchdir=${WORKDIR}/cargo_home \
            file://0001-Use-TARGET_CXXFLAGS-to-build-mozjs.patch;patchdir=${WORKDIR}/mozjs"

SRCREV = "dc0ba03d6816a3a2f2d9eff55c67af3daf3e96e6"

SRCREV_FORMAT .= "_media"
SRCREV_media = "45756bef67037ade0f4f0125d579fdc3f3d457c8"

SRCREV_FORMAT .= "_stylo"
SRCREV_stylo = "039959da0beac876128b86fa4b80908d8e55fbaf"

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

SRCREV_FORMAT .= "_webrender"
SRCREV_webrender = "9d354adf8955b1390dd56db89e6d5a9ea7880391"


# At the end of do_configure task cargo-common class takes all
# the git repos from the SRC_URI, and appends them to the end
# of cargo_home/config as repo patches for separate crates.
# This might work for building pure Rust applications, but not
# so much when the application comes with its own build setup
# (like Servo).
#
# To avoid this, save the SRC_URI variable before the do_configure
# task, and remove all the git repos from it. At the end of the do_configure
# task just restore it to its original value.
python saveSrcUri(){
  d.setVar("SRC_URI_BAK", d.getVar("SRC_URI"))
  all_uris = d.getVar("SRC_URI").split()

  uris_without_git = ""
  for uri in all_uris:
    if not uri.startswith("git://"):
      uris_without_git += " " + uri
      continue

  d.setVar("SRC_URI", uris_without_git)
}

python restoreSrcUri(){
  d.setVar("SRC_URI", d.getVar("SRC_URI_BAK"))
}

do_configure[prefuncs] += "saveSrcUri"
do_configure[postfuncs] += "restoreSrcUri"
