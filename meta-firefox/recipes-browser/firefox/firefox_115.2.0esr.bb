# Copyright (C) 2009-2015, O.S. Systems Software Ltda. All Rights Reserved
# Released under the MIT license (see packages/COPYING)

include firefox_crates_esr.inc
include firefox.inc

LIC_FILES_CHKSUM = "file://toolkit/content/license.html;md5=1b074cb88f7e9794d795c1346bcc9c80"

FILESEXTRAPATHS:prepend := "${THISDIR}/firefox-esr:"

SRC_URI[sha256sum] = "51534dd2a158d955a2cb67cc1308f100f6c9def0788713ed8b4d743f3ad72457"

SRCREV_application-services = "86c84c217036c12283d19368867323a66bf35883"
