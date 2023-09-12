# Copyright (C) 2009-2015, O.S. Systems Software Ltda. All Rights Reserved
# Released under the MIT license (see packages/COPYING)

include firefox_crates_esr.inc
include firefox.inc

LIC_FILES_CHKSUM = "file://toolkit/content/license.html;md5=1b074cb88f7e9794d795c1346bcc9c80"

FILESEXTRAPATHS:prepend := "${THISDIR}/firefox-esr:"

SRC_URI[sha256sum] = "5439b5b603ad7e60e85723a497f7ff761ad0a2cc8a685ffb5a1c269b1250c966"

SRCREV_application-services = "86c84c217036c12283d19368867323a66bf35883"
