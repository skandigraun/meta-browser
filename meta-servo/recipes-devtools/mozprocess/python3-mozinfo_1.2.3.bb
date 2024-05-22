
SUMMARY = "Library to get system information for use in Mozilla testing"
HOMEPAGE = "https://wiki.mozilla.org/Auto-tools/Projects/Mozbase"
AUTHOR = "Mozilla Automation and Testing Team <tools@lists.mozilla.org>"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://setup.py;md5=1ca14917dcb1c51665a2003acf678caa"

SRC_URI = "https://files.pythonhosted.org/packages/23/35/96cccb2244a08247f5c1b5e810d6117d35a30e4a3e29679ed0c7dd2406c6/mozinfo-1.2.3.tar.gz"
SRC_URI[md5sum] = "6e7d498e90c64a80c8cf8a41cddc9274"
SRC_URI[sha256sum] = "5d2b8a5f1b362692f221e33eb3ff47454a580db1a1384614cdc637b31131b438"

S = "${WORKDIR}/mozinfo-1.2.3"

RDEPENDS:${PN} = "python3-distro python3-mozfile"

inherit setuptools3
