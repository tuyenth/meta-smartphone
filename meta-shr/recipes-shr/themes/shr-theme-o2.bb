DESCRIPTION = "o2 theme suit for shr - it looks like om2007.2"
SECTION = "x11/data"
HOMEPAGE = "http://jmccloud.jm.funpic.de"
AUTHOR = "Jesus McCloud <bernd.pruenster@gmail.com"
RDEPENDS_${PN} = "phoneui-shr-theme-o2 elementary-theme-o2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r2"
inherit allarch

PV = "0.1"

ALLOW_EMPTY_${PN} = "1"

PNBLACKLIST[shr-theme-o2] ?= "Runtime depends on blacklisted phoneui-shr-theme-o2 - the recipe will be removed on 2017-09-01 unless the issue is fixed"

PNBLACKLIST[shr-theme-o2] ?= "Runtime depends on blacklisted shr-theme-o2 - the recipe will be removed on 2017-09-01 unless the issue is fixed"

PNBLACKLIST[shr-theme-o2] ?= "Runtime depends on blacklisted shr-theme-o2-dev - the recipe will be removed on 2017-09-01 unless the issue is fixed"
