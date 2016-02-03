package com.apelon.config;

import com.apelon.resourcemap.objects.ResourcemapField;
import com.apelon.resourcemap.objects.ResourcemapLayer;

/**
 * Created by vasili on 2/3/16.
 */
public class ResourceMapData {

    public static ResourcemapLayer tanzanian_regions_layer() {
        ResourcemapLayer layer_tanzanian_geographical = new ResourcemapLayer();
        layer_tanzanian_geographical.setCollectionId(Config.getResourcemapCollectionTanzania());
        layer_tanzanian_geographical.setLayerId(1816);
        layer_tanzanian_geographical.setLayerName("Geography"); //Medical Facility Information
        layer_tanzanian_geographical.setLayerOrder(1);

        ResourcemapField field_tanzanian_region = new ResourcemapField();
        field_tanzanian_region.setFieldCode("regions");
        field_tanzanian_region.setFieldName("regions");
        field_tanzanian_region.setFieldOrder(1);
        field_tanzanian_region.setFieldId(14413);
        field_tanzanian_region.setValuesetName(Config.getValuesetTanzaniaRegions());
        field_tanzanian_region.setNextId(2);
        layer_tanzanian_geographical.setUpdateField(field_tanzanian_region);

        return layer_tanzanian_geographical;
    }

    public static void buildData() {

        // BALI
        ResourcemapLayer layer_bali_geographical = new ResourcemapLayer();
        layer_bali_geographical.setCollectionId(Config.getResourcemapCollectionBali());
        layer_bali_geographical.setLayerId(1673);
        layer_bali_geographical.setLayerName("Geography"); //Medical Facility Information
        layer_bali_geographical.setLayerOrder(2);

        ResourcemapLayer layer_bali_facility = new ResourcemapLayer();
        layer_bali_facility.setCollectionId(Config.getResourcemapCollectionBali());
        layer_bali_facility.setLayerId(1670);
        layer_bali_facility.setLayerName("Medical Facility Information"); //Medical Facility Information
        layer_bali_facility.setLayerOrder(2);

        ResourcemapField field_bali_facility_type = new ResourcemapField();
        field_bali_facility_type.setFieldName("Facility Type");
        field_bali_facility_type.setFieldOrder(1);
        field_bali_facility_type.setFieldId(14413);
        field_bali_facility_type.setValuesetName(Config.getValuesetSnomedFacilityTypes());
        field_bali_facility_type.setNextId(1);
        layer_bali_facility.setUpdateField(field_bali_facility_type);

        // MANILLA
        ResourcemapLayer layer_manilla_geographical = new ResourcemapLayer();
        layer_manilla_geographical.setCollectionId(Config.getResourcemapCollectionBali());
        layer_manilla_geographical.setLayerId(1673);
        layer_manilla_geographical.setLayerName("Geography"); //Medical Facility Information
        layer_manilla_geographical.setLayerOrder(1);

        ResourcemapLayer layer_manilla_facility = new ResourcemapLayer();
        layer_manilla_facility.setCollectionId(Config.getResourcemapCollectionManilla());
        layer_manilla_facility.setLayerId(1669);
        layer_manilla_facility.setLayerName("Medical Facility Information"); //Medical Facility Information
        layer_manilla_facility.setLayerOrder(2);

        ResourcemapField field_manilla_facility_type = new ResourcemapField();
        field_manilla_facility_type.setFieldCode("facility_type");
        field_manilla_facility_type.setFieldName("Facility Type");
        field_manilla_facility_type.setFieldOrder(1);
        field_manilla_facility_type.setFieldId(14371);
        field_manilla_facility_type.setValuesetName(Config.getValuesetSnomedFacilityTypes());
        field_manilla_facility_type.setNextId(82);
        layer_manilla_facility.setUpdateField(field_manilla_facility_type);


        //TANZANIAN


        ResourcemapLayer layer_tanzanian_facility = new ResourcemapLayer();
        layer_tanzanian_facility.setCollectionId(Config.getResourcemapCollectionBali());
        layer_tanzanian_facility.setLayerId(1817);
        layer_tanzanian_facility.setLayerName("Facility Type"); //Medical Facility Information
        layer_tanzanian_facility.setLayerOrder(2);
        ResourcemapField field_tanzanian_facility_type = new ResourcemapField();
        field_tanzanian_facility_type.setFieldCode("facility_type");
        field_tanzanian_facility_type.setFieldName("Facility Type");
        field_tanzanian_facility_type.setFieldOrder(1);
        field_tanzanian_facility_type.setFieldId(14414);
        field_tanzanian_facility_type.setValuesetName(Config.getValuesetSnomedFacilityTypes());
        field_tanzanian_facility_type.setNextId(3);
        layer_tanzanian_facility.setUpdateField(field_tanzanian_facility_type);


    }

}
