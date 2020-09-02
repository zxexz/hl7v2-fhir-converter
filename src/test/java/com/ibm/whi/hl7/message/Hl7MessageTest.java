package com.ibm.whi.hl7.message;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import org.python.google.common.collect.Lists;
import com.ibm.whi.hl7.resource.ResourceModel;
import ca.uhn.hl7v2.HL7Exception;


public class Hl7MessageTest {

  @Test
  public void test_patient() throws HL7Exception, IOException {

    ResourceModel rsm = ResourceModel.generateResourceModel("resource/Patient");
    FHIRResource patient = new FHIRResource("Patient", "PID", rsm, 0);
    Hl7Message message = new Hl7Message("ADT", Lists.newArrayList(patient));
    String hl7message = "MSH|^~\\&|hl7Integration|hl7Integration|||||ADT^A01|||2.3|\r"
        + "EVN|A01|20130617154644\r"
        + "PID|1|465 306 5961|000010016^^^MR~000010017^^^MR~000010018^^^MR|407623|Wood^Patrick^^Sr^MR||19700101|female|||High Street^^Oxford^^Ox1 4DP~George St^^Oxford^^Ox1 5AP|||||||\r"
        + "NK1|1|Wood^John^^^MR|Father||999-9999\r" + "NK1|2|Jones^Georgie^^^MSS|MOTHER||999-9999\r"
        + "PV1|1||Location||||||||||||||||261938_6_201306171546|||||||||||||||||||||||||20130617134644|||||||||";
    System.out.println(message.convertMessageToFHIRResource(hl7message));
  }


  @Test
  public void test_patient_encounter() throws HL7Exception, IOException {

    ResourceModel rsm= ResourceModel.generateResourceModel("resource/Patient");
    FHIRResource patient = new FHIRResource("Patient", "PID", rsm, 0);
    ResourceModel encounter = ResourceModel.generateResourceModel("resource/Encounter");
    FHIRResource encounterFH =
        new FHIRResource("Encounter", "PV1", encounter, 0, false, Lists.newArrayList("PV2"));



    Hl7Message message = new Hl7Message("ADT", Lists.newArrayList(patient, encounterFH));
    String hl7message = "MSH|^~\\&|SE050|050|PACS|050|201209121212||ADT^A01|102|T|2.7|||AL|NE\r"
        + "EVN||201209122222\r"
        + "PID|0010||ADTNew^^^1231||ADT01New||19800202|F||W|111 TEST_STREET_NAME^^TEST_CITY^NY^111-1111^USA||(905)111-1111|||S|ZZ|12^^^124|34-13-312||||TEST_BIRTH_PLACE\r"
        + "PV1|1|ff|yyy|EL|||200^ATTEND_DOC_FAMILY_TEST^ATTEND_DOC_GIVEN_TEST|201^REFER_DOC_FAMILY_TEST^REFER_DOC_GIVEN_TEST|202^CONSULTING_DOC_FAMILY_TEST^CONSULTING_DOC_GIVEN_TEST|MED|||||B6|E|272^ADMITTING_DOC_FAMILY_TEST^ADMITTING_DOC_GIVEN_TEST||48390|||||||||||||||||||||||||201409122200|20000206031726\r"
        + "AL1|0001|DA|98798^problem|SV|sneeze|20120808\r";
    System.out.println(message.convertMessageToFHIRResource(hl7message));
  }


  @Test
  public void test_patient_encounter_only() throws HL7Exception, IOException {


    ResourceModel encounter = ResourceModel.generateResourceModel("resource/Encounter");
    FHIRResource encounterFH =
        new FHIRResource("Encounter", "PV1", encounter, 0, false, Lists.newArrayList("PV2"));



    Hl7Message message = new Hl7Message("ADT", Lists.newArrayList(encounterFH));
    String hl7message = "MSH|^~\\&|SE050|050|PACS|050|201209121212||ADT^A01|102|T|2.7|||AL|NE\r"
        + "EVN||201209122222\r"
        + "PID|0010||ADTNew^^^1231||ADT01New||19800202|F||W|111 TEST_STREET_NAME^^TEST_CITY^NY^111-1111^USA||(905)111-1111|||S|ZZ|12^^^124|34-13-312||||TEST_BIRTH_PLACE\r"
        + "PV1|1|ff|Location|EL|||200^ATTEND_DOC_FAMILY_TEST^ATTEND_DOC_GIVEN_TEST|201^REFER_DOC_FAMILY_TEST^REFER_DOC_GIVEN_TEST|202^CONSULTING_DOC_FAMILY_TEST^CONSULTING_DOC_GIVEN_TEST|MED|||||B6|E|272^ADMITTING_DOC_FAMILY_TEST^ADMITTING_DOC_GIVEN_TEST||48390|||||||||||||||||||||||||201409122200|20000206031726\r"
        + "AL1|0001|DA|98798^problem|SV|sneeze|20120808\r";
    System.out.println(message.convertMessageToFHIRResource(hl7message));
  }


  @Test
  public void test_observation() throws HL7Exception, IOException {

    ResourceModel rsm = ResourceModel.generateResourceModel("resource/Observation");
    FHIRResource observation = new FHIRResource("Observation", "OBX", rsm, 0);
    Hl7Message message = new Hl7Message("ADT", Lists.newArrayList(observation));
    String hl7message = "MSH|^~\\&|hl7Integration|hl7Integration|||||ADT^A01|||2.3|\r"
        + "EVN|A01|20130617154644\r"
        + "PID|1|465 306 5961|000010016^^^MR~000010017^^^MR~000010018^^^MR|407623|Wood^Patrick^^Sr^MR||19700101|female|||High Street^^Oxford^^Ox1 4DP~George St^^Oxford^^Ox1 5AP|||||||\r"
        + "NK1|1|Wood^John^^^MR|Father||999-9999\r" + "NK1|2|Jones^Georgie^^^MSS|MOTHER||999-9999\r"
        + "PV1|1||Location||||||||||||||||261938_6_201306171546|||||||||||||||||||||||||20130617134644|||||||||\r"
        + "OBX|1|TX|1234||First line: ECHOCARDIOGRAPHIC REPORT||||||F||\r"
        + "OBX|2|TX|||Second Line: NORMAL LV CHAMBER SIZE WITH MILD CONCENTRIC LVH\\.br\\Third Line in the same field, after the escape character for line break.||||||F||\r"
        + "OBX|3|TX|||Fourth Line: HYPERDYNAMIC LV SYSTOLIC FUNCTION, VISUAL EF 80%~Fifth line, as part of a repeated field||||||F||";
    System.out.println(message.convertMessageToFHIRResource(hl7message));
  }


  @Test
  public void test_observation_multiple() throws HL7Exception, IOException {

    ResourceModel rsm = ResourceModel.generateResourceModel("resource/Observation");
    FHIRResource observation =
        new FHIRResource("Observation", "OBX", rsm, 0, true, new ArrayList<>());
    Hl7Message message = new Hl7Message("ADT", Lists.newArrayList(observation));
    String hl7message = "MSH|^~\\&|hl7Integration|hl7Integration|||||ADT^A01|||2.3|\r"
        + "EVN|A01|20130617154644\r"
        + "PID|1|465 306 5961|000010016^^^MR~000010017^^^MR~000010018^^^MR|407623|Wood^Patrick^^Sr^MR||19700101|female|||High Street^^Oxford^^Ox1 4DP~George St^^Oxford^^Ox1 5AP|||||||\r"
        + "NK1|1|Wood^John^^^MR|Father||999-9999\r" + "NK1|2|Jones^Georgie^^^MSS|MOTHER||999-9999\r"
        + "PV1|1||Location||||||||||||||||261938_6_201306171546|||||||||||||||||||||||||20130617134644|||||||||\r"
        + "OBX|1|TX|1234||First line: ECHOCARDIOGRAPHIC REPORT||||||F||\r"
        + "OBX|2|TX|||Second Line: NORMAL LV CHAMBER SIZE WITH MILD CONCENTRIC LVH\\.br\\Third Line in the same field, after the escape character for line break.||||||F||\r"
        + "OBX|3|TX|||Fourth Line: HYPERDYNAMIC LV SYSTOLIC FUNCTION, VISUAL EF 80%~Fifth line, as part of a repeated field||||||F||";
    System.out.println(message.convertMessageToFHIRResource(hl7message));
  }

}
