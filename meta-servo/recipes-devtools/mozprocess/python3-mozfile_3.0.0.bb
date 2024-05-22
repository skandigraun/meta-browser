
SUMMARY = "Library of file utilities for use in Mozilla testing"
HOMEPAGE = "https://wiki.mozilla.org/Auto-tools/Projects/Mozbase"
AUTHOR = "Mozilla Automation and Tools team <tools@lists.mozilla.org>"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://setup.py;md5=56148bb75905df64710098b76cf819c6"

SRC_URI = "https://files.pythonhosted.org/packages/25/f8/a1f0076490d50dbe8bdcf15df97856a4734f459aaf0a4d42c64a11ab7231/mozfile-3.0.0.tar.gz"
SRC_URI[md5sum] = "f36800b6c33b3e52bf0181da20087c5f"
SRC_URI[sha256sum] = "92ca1a786abbdf5e6a7aada62d3a4e28f441ef069c7623223add45268e53c789"

S = "${WORKDIR}/mozfile-3.0.0"

RDEPENDS:${PN} = "python3-six"

inherit setuptools3
