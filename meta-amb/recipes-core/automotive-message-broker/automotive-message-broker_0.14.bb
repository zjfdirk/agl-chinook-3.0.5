SUMMARY = "Automotive Message Broker"
DESCRIPTION = "Automotive-message-broker abstracts the details of the network \
away from applications and provides a standard API for applications to easily \
get the required information"
HOMEPAGE = "https://01.org/automotive-message-broker"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=b42382de5d854b9bb598acf2e8827de3"

inherit cmake systemd

PV = "0.14"

DEPENDS = "glib-2.0 util-linux sqlite3 qtbase boost libtool"

SRC_URI = "git://github.com/otcshare/automotive-message-broker.git;protocol=git;branch=0.14"
SRCREV = "c216955d16ca275159891cad296217094d972390"

S = "${WORKDIR}/git"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "${S}/packaging/ambd.service"

EXTRA_OECMAKE = " \
                 -Dqtmainloop=ON \
                 -Ddatabase_plugin=ON \
                 -Dgpsnmea_plugin=ON \
                 -Dobd2_plugin=ON \
                 -Dusebluez5=ON \
                "

do_install_append() {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/ambd.service ${D}${systemd_unitdir}/system
}


PACKAGES += " \
             ${PN}-dev \
             ${PN}-plugins-qtmainloop \
             ${PN}-plugins \
             ${PN}-plugins-common \
             ${PN}-plugins-gpsnmea \
             ${PN}-plugins-database \
             ${PN}-plugins-obd2 \
            "

FILES_${PN} = " \
              ${bindir}/ambd \
              ${libdir}/libamb.so* \
              ${systemd_unitdir}/ambd.service \
              ${bindir}/ambctl \
             "

FILES_${PN}-dev = " \
                   ${includedir}/amb/*.h \
                   ${includedir}/amb/*.hpp \
                   ${libdir}/pkgconfig/*.pc \
                  "

FILES_${PN}-plugins-qtmainloop = " \
                                  ${libdir}/${PN}/qtmainloopplugin.so \
                                 "

FILES_${PN}-plugins = " \
                       ${libdir}/${PN}/examplesourceplugin.so \
                       ${libdir}/${PN}/examplesinkplugin.so \
                       ${libdir}/${PN}/dbussinkplugin.so \
                       ${libdir}/${PN}/demosinkplugin.so \
                       ${sysconfdir}/dbus-1/system.d/amb.conf \
                       ${sysconfdir}/ambd/plugins.d/dbus \
                       ${sysconfdir}/ambd/plugins.d/examplesink \
                       ${sysconfdir}/ambd/plugins.d/examplesource \
                      "

FILES_${PN}-plugins-common = " \
                              ${libdir}/libamb-plugins-common.so \
                             "



FILES_${PN}-plugins-obd2 = " \
                            ${libdir}/${PN}/obd2sourceplugin.so \
                            ${sysconfdir}/ambd/plugins.d/obd2 \
                           "

FILES_${PN}-plugins-database = " \
                            ${libdir}/${PN}/databasesinkplugin.so \
                            ${sysconfdir}/ambd/plugins.d/database \
                           "

FILES_${PN}-plugins-gpsnmea = " \
                            ${libdir}/${PN}/gpsnmea.so \
                            ${sysconfdir}/ambd/plugins.d/gpsnmea \
                           "



