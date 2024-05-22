
SUMMARY = "Python logging made (stupidly) simple"
HOMEPAGE = "https://github.com/Delgan/loguru"
AUTHOR = "Delgan <delgan.py@gmail.com>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4c50b6bd2e1e6481b0c7ed7691425651"

SRC_URI = "https://files.pythonhosted.org/packages/9e/30/d87a423766b24db416a46e9335b9602b054a72b96a88a241f2b09b560fa8/loguru-0.7.2.tar.gz"
SRC_URI[md5sum] = "572c233002a3ab88ffb727a916716ad5"
SRC_URI[sha256sum] = "e671a53522515f34fd406340ee968cb9ecafbc4b36c679da03c18fd8d0bd51ac"

S = "${WORKDIR}/loguru-0.7.2"

inherit setuptools3

BBCLASSEXTEND = "native"
