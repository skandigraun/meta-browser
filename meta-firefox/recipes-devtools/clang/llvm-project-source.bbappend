FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += '${@bb.utils.contains_any("LAYERSERIES_CORENAMES", "dunfell kirkstone", " file://0001-fix-__STDC_LIMIT_MACROS-macro-redifined.patch", "", d)}'
