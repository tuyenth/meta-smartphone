LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

# in case this recipe is built with DISTRO-less config or different DISTRO
DISTRO_RELEASE ?= "not-SHR"

PV = "${DISTRO_RELEASE}"

PACKAGES = "${PN}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install() {
    mkdir -p ${D}${sysconfdir}
    echo "SHR ${DISTRO_VERSION}" > ${D}${sysconfdir}/shr-version
    echo "Built from branch: ${METADATA_BRANCH}" >> ${D}${sysconfdir}/shr-version
    echo "Revision: ${METADATA_REVISION}" >> ${D}${sysconfdir}/shr-version
}
