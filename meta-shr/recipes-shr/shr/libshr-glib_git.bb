DESCRIPTION = "GLib-based DBus bindings for shr-project.org"
AUTHOR = "Klaus 'mrmoku' Kurzmann"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fad9b3332be894bab9bc501572864b29"
SECTION = "devel"
DEPENDS = "glib-2.0-native glib-2.0 fso-specs shr-specs"
SRCREV = "0730d15507270a307861d9bc1b6af84d9977c622"
PV = "2011.03.08.2+gitr${SRCPV}"
PR = "r2"

inherit autotools pkgconfig

SRC_URI = "git://git.shr-project.org/repo/libshr-glib.git;protocol=http;branch=master"
S = "${WORKDIR}/git"

do_configure_prepend() {
       sed -i -e s#SHR_SPECS_DIR="#SHR_SPECS_DIR="${STAGING_DIR_HOST}/#g ${S}/configure.ac
}

PNBLACKLIST[libshr-glib] ?= "Sometimes fails as in http://errors.yoctoproject.org/Errors/Details/141196/ - the recipe will be removed on 2017-09-01 unless the issue is fixed""
