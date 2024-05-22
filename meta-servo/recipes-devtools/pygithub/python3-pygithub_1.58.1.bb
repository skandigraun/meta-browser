
SUMMARY = "Use the full Github API v3"
HOMEPAGE = "https://github.com/pygithub/pygithub"
AUTHOR = "Vincent Jacques <vincent@vincent-jacques.net>"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://PyGithub-1.58.1.dist-info/COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://files.pythonhosted.org/packages/af/09/5f5a51e8e7a3245cc1af0c5c71b4f6bbcd3c96c5317e4c4e864c728aa1e5/PyGithub-1.58.1-py3-none-any.whl;downloadfilename=PyGithub-1.58.1.zip"
SRC_URI[md5sum] = "b5c497c15c1d75d407979009ee799ff9"
SRC_URI[sha256sum] = "4e7fe9c3ec30d5fde5b4fbb97f18821c9dbf372bf6df337fe66f6689a65e0a83"

S = "${WORKDIR}"

inherit python3-dir

do_install(){
  install -d ${D}${PYTHON_SITEPACKAGES_DIR}/PyGithub-1.58.1.dist-info
  install -d ${D}${PYTHON_SITEPACKAGES_DIR}/github
  find ./github -exec install -m 0644 {} ${D}${PYTHON_SITEPACKAGES_DIR}/github/ \;
  find ./PyGithub-1.58.1.dist-info -exec install -m 0644 {} ${D}${PYTHON_SITEPACKAGES_DIR}/PyGithub-1.58.1.dist-info/ \;
}

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/PyGithub-1.58.1.dist-info/*"
FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/github/*"
