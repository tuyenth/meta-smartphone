DESCRIPTION = "Hybris is a solution that commits hybris, by allowing us to use \
bionic-based HW adaptations in glibc systems"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "d182c5312cd90962997e40ecd1fd3e586328cf68"
PV = "0.1.0+gitr${SRCPV}"
PR = "r1"
PE = "1"

SRC_URI = "git://github.com/libhybris/libhybris;branch=master;protocol=git"
S = "${WORKDIR}/git/hybris"

PROVIDES += "virtual/libgles1 virtual/libgles2 virtual/egl"

# We don't ship any android binaries but depend on someone else doing this
# Your image needs to pull right MACHINE specific implementation
# use VIRTUAL-RUNTIME_android-system-image in some packagegroup
# RDEPENDS cannot be used because this is TUNE_PKGARCH recipe and 
# android-system-image is MACHINE_ARCH
# RDEPENDS_${PN} += "${VIRTUAL-RUNTIME_android-system-image}"

inherit autotools