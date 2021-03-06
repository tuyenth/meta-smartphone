DESCRIPTION = "Sówka elementary theme"
SECTION = "e/utils"
DEPENDS = "edje-native"
LICENSE = "MIT & BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f523ab5986cc79b52a90d2ac3d5454a2"
SRCREV = "4e6035ef5452b2f0a128dff91ddeb8335a3aafec"
PV = "0.2+gitr${SRCPV}"
inherit allarch

SRC_URI = "git://git.shr-project.org/repo/shr-themes.git;protocol=http;branch=master"

S = "${WORKDIR}/git/elementary/${PN}"

do_compile() {
    ${STAGING_BINDIR_NATIVE}/edje_cc -id ${S}/images/. -fd ${S}/fonts/. ${S}/sowka.edc -o ${S}/sowka.edj
}

do_install() {
    install -d ${D}${datadir}/elementary/themes/
    install -m 0644 ${S}/sowka.edj ${D}${datadir}/elementary/themes/
}

FILES_${PN} = "${datadir}/elementary/themes/sowka.edj"

PNBLACKLIST[elementary-theme-sowka] ?= "Depends on blacklisted edje-native - the recipe will be removed on 2017-09-01 unless the issue is fixed"

PNBLACKLIST[elementary-theme-sowka] ?= "Runtime depends on blacklisted elementary-theme-sowka-dev - the recipe will be removed on 2017-09-01 unless the issue is fixed"

PNBLACKLIST[elementary-theme-sowka] ?= "Runtime depends on blacklisted elementary-theme-sowka - the recipe will be removed on 2017-09-01 unless the issue is fixed"
