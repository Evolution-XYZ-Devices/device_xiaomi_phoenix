ifeq (true,$(PHOENIX_DEBUG))
    VENDOR_LOG_LEVEL_I=I
    VENDOR_LOG_LEVEL_D=D
else
    VENDOR_LOG_LEVEL_I=S
    VENDOR_LOG_LEVEL_D=S
endif

PRODUCT_PROPERTY_OVERRIDES += \
    persist.log.tag.DeviceStatisticsService=$(VENDOR_LOG_LEVEL_I) \
    persist.log.tag.QC-time-services=$(VENDOR_LOG_LEVEL_I) \
    persist.log.tag.DrmLibTime=$(VENDOR_LOG_LEVEL_D) \
    persist.log.tag.bt_stack=$(VENDOR_LOG_LEVEL_I) \
    persist.log.tag.CamX=$(VENDOR_LOG_LEVEL_D) \
    persist.log.tag.cnss-daemon=$(VENDOR_LOG_LEVEL_D) \
    persist.log.tag.ultrasound_extn=$(VENDOR_LOG_LEVEL_D)
