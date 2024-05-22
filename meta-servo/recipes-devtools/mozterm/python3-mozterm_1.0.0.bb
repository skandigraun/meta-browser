
SUMMARY = "Terminal abstractions built around the blessings module."
HOMEPAGE = ""
AUTHOR = "Andrew Halberstadt <ahalberstadt@mozilla.com>"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://setup.py;md5=1b8692dbe8116461c9c65b22c86569d1"

SRC_URI = "https://files.pythonhosted.org/packages/a0/69/5ff6001df98cf1894e6fb4aa74eda1504f830515e52fc6b0a3acc8c1a788/mozterm-1.0.0.tar.gz"
SRC_URI[md5sum] = "8fa5fb30e3b8c220496961380fe362b8"
SRC_URI[sha256sum] = "b1e91acec188de07c704dbb7b0100a7be5c1e06567b3beb67f6ea11d00a483a4"

S = "${WORKDIR}/mozterm-1.0.0"

RDEPENDS:${PN} = "python3-six"

inherit setuptools3

BBCLASSEXTEND = "native"
