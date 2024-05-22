
SUMMARY = "Cross-platform desktop notification library for Python"
HOMEPAGE = "https://github.com/ms7m/notify-py"
AUTHOR = "Mustafa Mohamed <mustafa@ms7m.me>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2992fec1edc1d6f43a14e4592e79ab74"

SRC_URI = "https://files.pythonhosted.org/packages/1a/d8/846ca34515305454d7d86c98ed0058eb8ef02175179e7a433b29d791066d/notify_py-0.3.42.tar.gz"
SRC_URI[md5sum] = "37df5b53f763a666359aa04520aa9b51"
SRC_URI[sha256sum] = "f49457f1d0577a150df5c3fd58ed2892ce3106a517f8e2083f61743d1c05be36"

S = "${WORKDIR}/notify_py-0.3.42"

RDEPENDS:${PN} = "python3-loguru bash"

inherit setuptools3

BBCLASSEXTEND = "native"
