
SUMMARY = "Robust log handling specialized for logging in the Mozilla universe"
HOMEPAGE = "https://wiki.mozilla.org/Auto-tools/Projects/Mozbase"
AUTHOR = "Mozilla Automation and Testing Team <tools@lists.mozilla.org>"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://setup.py;md5=c0c1c35d48d5d25c6ba6169e00324578"

SRC_URI = "https://files.pythonhosted.org/packages/4a/d6/198a58f197afb9482050671c4a9b0ee299c005cf39c4113589b726e9f915/mozlog-8.0.0.tar.gz"
SRC_URI[md5sum] = "5f4bd52b25ac7ae23ba99f95da83fa80"
SRC_URI[sha256sum] = "26e5e9586afe2d6359a3d75aa6ea25aa2904d0062d0a158418682e44458d98e9"

S = "${WORKDIR}/mozlog-8.0.0"

inherit setuptools3

BBCLASSEXTEND = "native"
