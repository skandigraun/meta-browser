
SUMMARY = "Mozilla-authored process handling"
HOMEPAGE = "https://wiki.mozilla.org/Auto-tools/Projects/Mozbase"
AUTHOR = "Mozilla Automation and Tools team <tools@lists.mozilla.org>"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://setup.py;md5=c8dbc490f5d5670c053e935027797e6c"

SRC_URI = "https://files.pythonhosted.org/packages/ae/a0/72e23715343b34f702f95399d1ce49c1cd12868048548a009d538edbb842/mozprocess-1.3.1.tar.gz"
SRC_URI[md5sum] = "c0c8b567d45366f10aed7697c4de4839"
SRC_URI[sha256sum] = "7dc38ec3c11693e9944ade1558392c04f37fd8df68d3ec7a20372dfe96b2e5bb"

S = "${WORKDIR}/mozprocess-1.3.1"

RDEPENDS:${PN} = "python3-mozinfo"

inherit setuptools3
