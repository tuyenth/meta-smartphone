require cornucopia-base.inc
require cornucopia-from-git.inc

DESCRIPTION = "mterm is a versatile muxer-aware terminal program"
HOMEPAGE = "http://www.freesmartphone.org/index.php/Implementations/fso-term"
AUTHOR = "Michael 'Mickey' Lauer <mlauer@vanille-media.de>"
SECTION = "console/network"
DEPENDS = "dbus dbus-glib readline libfsoframework"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
SRCREV = "${FSO_CORNUCOPIA_SRCREV}"
PV = "1.9.0+gitr${SRCPV}"
PR = "${INC_PR}.1"

S = "${WORKDIR}/git/tools/${PN}"

FILES_${PN} += "${datadir}"
# autotools-brokensep
B = "${S}"

PNBLACKLIST[mterm2] ?= "Depends on blacklisted libfsoframework - the recipe will be removed on 2017-09-01 unless the issue is fixed"
