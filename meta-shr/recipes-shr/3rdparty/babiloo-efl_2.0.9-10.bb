DESCRIPTION = "EFL Dictionary Viewer. It supports dictionaries in SDictionary and StarDict format."
SECTION = "devel/python"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
AUTHOR = "Luca Vaudano <vaudano@gmail.com>"
HOMEPAGE = "http://babiloo-project.org"
RDEPENDS_${PN} = "python-elementary python-compression python-misc python-netclient"

PV = "2.0.9-10"
PR = "r5"

SRC_URI = "http://www.vaudano.eu/projects/babiloo/babiloo_2.0.9-10.tar.gz;name=tarball"
SRC_URI[tarball.md5sum] = "2a66cc5863aca83c4b3120b8ca974676"
SRC_URI[tarball.sha256sum] = "42bba5f09875c8d3a8d4579a79ade86207a095b6d4741cc3a72bee071c22bb46"

S = "${WORKDIR}/babiloo"

do_install() {
    install -d "${D}${datadir}/babiloo"
    install -d "${D}${datadir}/babiloo/dicts"

    cp -R --no-dereference --preserve=mode,links -v "${S}/core" "${D}${datadir}/babiloo/"
    cp -R --no-dereference --preserve=mode,links -v "${S}/efl" "${D}${datadir}/babiloo/"
    cp -R --no-dereference --preserve=mode,links -v "${S}/images" "${D}${datadir}/babiloo/"
    install -m 0755 "${S}/run.py" "${D}${datadir}/babiloo/"
    install -d "${D}${bindir}"
    ln -s "${datadir}/babiloo/run.py" "${D}${bindir}/babiloo"
    install -d "${D}${datadir}/pixmaps"
    install -m 0644 "${S}/images/babiloo.png" "${D}${datadir}/pixmaps"
    install -d "${D}${datadir}/applications"
    install -m 0644 "${S}/babiloo.desktop" "${D}${datadir}/applications"

    cp -R --no-dereference --preserve=mode,links -v "${S}/locale" "${D}${datadir}/"
    find ${D}${datadir}/locale -name *.po -exec rm {} \;
    rm -f ${D}${datadir}/locale/babiloo.pot
}

FILES_${PN} += "${datadir}/babiloo"

PNBLACKLIST[babiloo-efl] ?= "Runtime depends on blacklisted python-elementary - the recipe will be removed on 2017-09-01 unless the issue is fixed"
