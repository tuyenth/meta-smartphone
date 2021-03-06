DESCRIPTION = "illume SHR theme"
SECTION = "e/utils"
DEPENDS = "edje-native"
LICENSE = "MIT & BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f523ab5986cc79b52a90d2ac3d5454a2"
SRCREV = "4e6035ef5452b2f0a128dff91ddeb8335a3aafec"
PV = "0.2+gitr${SRCPV}"
inherit allarch

SRC_URI = "git://git.shr-project.org/repo/shr-themes.git;protocol=http;branch=master"

S = "${WORKDIR}/git/e-wm/${PN}"

do_compile() {
    ${STAGING_BINDIR_NATIVE}/edje_cc -id ${S}/images/. -fd ${S}/fonts/. ${S}/illume-sixteen.edc -o ${S}/illume-sixteen.edj
}

do_install() {
    install -d ${D}${datadir}/enlightenment/data/themes/
    install -m 0644 ${S}/illume-sixteen.edj ${D}${datadir}/enlightenment/data/themes/
}

FILES_${PN} = "${datadir}/enlightenment/data/themes/illume-sixteen.edj"



PNBLACKLIST[e-wm-theme-illume-sixteen] ?= "Depends on blacklisted edje-native - the recipe will be removed on 2017-09-01 unless the issue is fixed"

PNBLACKLIST[e-wm-theme-illume-sixteen] ?= "Runtime depends on blacklisted e-wm-theme-illume-sixteen - the recipe will be removed on 2017-09-01 unless the issue is fixed"

PNBLACKLIST[e-wm-theme-illume-sixteen] ?= "Runtime depends on blacklisted e-wm-theme-illume-sixteen-dev - the recipe will be removed on 2017-09-01 unless the issue is fixed"
