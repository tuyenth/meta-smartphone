SUPPORTED_EXTRA_ARCHS ?= "armv4t arm920tt armv5te armv6-novfp armv7a-vfp-neon cortexa8-vfp-neon cortexa8t-vfp-neon cortexa9-vfp-neon cortexa9t-vfp-neon x86_64 i586"

do_compile() {
    mkdir -p ${S}/${sysconfdir}/opkg
    for feed in all ${MACHINE_ARCH}; do
        echo "src/gz ${DISTRO_FEED_PREFIX}-${feed} ${DISTRO_FEED_URI}/${feed}" > ${S}/${sysconfdir}/opkg/${feed}-feed.conf
    done
    for feed in ${PACKAGE_EXTRA_ARCHS}; do
        echo " ${SUPPORTED_EXTRA_ARCHS} " | grep -q " ${feed} " && echo "src/gz ${DISTRO_FEED_PREFIX}-${feed} ${DISTRO_FEED_URI}/${feed}" > ${S}/${sysconfdir}/opkg/${feed}-feed.conf
    done
    # in case last grep -q was returning error
    exit 0
}

CONFFILES_${PN} = '${@ " ".join( [ ( "${sysconfdir}/opkg/%s-feed.conf" % feed ) for feed in "all ${MACHINE_ARCH}".split() ] ) }'
CONFFILES_${PN} += '${@ " ".join( [ ( "${sysconfdir}/opkg/%s-feed.conf" % feed ) for feed in set("${PACKAGE_EXTRA_ARCHS}".split()).intersection(set("${SUPPORTED_EXTRA_ARCHS}".split())) ] ) }'
