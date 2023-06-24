#
# Copyright (C) 2020 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)
$(call inherit-product, device/xiaomi/phoenix/device.mk)

# Inherit some common Evolution X stuff.
$(call inherit-product, vendor/evolution/config/common_full_phone.mk)

# Evolution X Build Type & Maintainer stuff.
EVO_BUILD_TYPE := Unofficial

# Gapps Build Details.
TARGET_USES_MINNI_GAPPS := true
TARGET_GAPPS_ARCH := arm64

# Custom stuff.
TARGET_BUILD_APERTURE_CAMERA := false
TARGET_USE_PIXEL_FINGERPRINT := true
TARGET_INCLUDE_PIXEL_CHARGER := true
TARGET_SUPPORTS_QUICK_TAP := true
TARGET_ENABLE_BLUR := true

# Boot animation
TARGET_SCREEN_HEIGHT := 2400
TARGET_SCREEN_WIDTH := 1080
TARGET_BOOT_ANIMATION_RES := 1080

# GMS
PRODUCT_GMS_CLIENTID_BASE := android-xiaomi

# Build Fingerprint.
PRODUCT_BUILD_PROP_OVERRIDES += \
    PRIVATE_BUILD_DESC="raven-user 13 TP1A.221005.002/9012097 release-keys"

BUILD_FINGERPRINT := google/raven/raven:13/TP1A.221005.002/9012097:user/release-keys

# Device identifier. This must come after all inclusions.
PRODUCT_NAME := evolution_phoenix
PRODUCT_DEVICE := phoenix
PRODUCT_BRAND := POCO
PRODUCT_MODEL := POCO X2
PRODUCT_MANUFACTURER := Xiaomi