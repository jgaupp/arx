/*
 * ARX: Powerful Data Anonymization
 * Copyright 2012 - 2016 Fabian Prasser, Florian Kohlmayer and contributors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.deidentifier.arx.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;

import org.deidentifier.arx.ARXConfiguration;
import org.deidentifier.arx.ARXFinancialConfiguration;
import org.deidentifier.arx.Data;
import org.deidentifier.arx.DataSubset;
import org.deidentifier.arx.criteria.FinancialJournalistNoAttackPrivacy;
import org.deidentifier.arx.criteria.FinancialJournalistPrivacy;
import org.deidentifier.arx.criteria.FinancialProsecutorNoAttackPrivacy;
import org.deidentifier.arx.criteria.FinancialProsecutorPrivacy;
import org.deidentifier.arx.metric.Metric;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test for anonymization based on a financial cost/benefit analysis
 *
 * @author Fabian Prasser
 * @author Florian Kohlmayer
 */
@RunWith(Parameterized.class)
public class TestAnonymizationFinancialPrivacy extends AbstractAnonymizationTest {
    
    /**
     * Returns a set of tests
     * @return
     * @throws IOException 
     */
    @Parameters(name = "{index}:[{0}]")
    public static Collection<Object[]> cases() throws IOException {
        return Arrays.asList(new Object[][] {
                                              /* 0 */{ new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/adult.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/adult_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig1()), "occupation", "./data/adult.csv", 2974380.2122727707, new int[] { 1, 3, 1, 1, 3, 2, 1, 1 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createLossMetric()).addCriterion(new FinancialJournalistPrivacy(DataSubset.create(Data.create("./data/adult.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/adult_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig2()), "occupation", "./data/adult.csv", 0.20171471140567654, new int[] { 0, 4, 0, 0, 2, 1, 0, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createPrecomputedEntropyMetric(0.1d, false)).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig1()), "occupation", "./data/adult.csv", 0.0, new int[] { 0, 0, 0, 0, 0, 0, 0, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/adult.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/adult_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig3()), "occupation", "./data/adult.csv", 289703.28175376146, new int[] { 0, 1, 1, 2, 3, 2, 1, 1 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/adult.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/adult_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig2()), "occupation", "./data/adult.csv", 2042388.7119921804, new int[] { 1, 3, 0, 1, 3, 0, 0, 1 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createEntropyBasedInformationLossMetric()).addCriterion(new FinancialJournalistPrivacy(DataSubset.create(Data.create("./data/adult.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/adult_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig4()), "occupation", "./data/adult.csv", 1982.8859574224414, new int[] { 1, 3, 1, 0, 3, 1, 1, 0 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createEntropyBasedInformationLossMetric()).addCriterion(new FinancialProsecutorNoAttackPrivacy()), "Highest level of school completed", "./data/atus.csv", 111422.02307271949, new int[] { 0, 3, 0, 0, 0, 0, 0, 1 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createLossMetric()).addCriterion(new FinancialJournalistPrivacy(DataSubset.create(Data.create("./data/atus.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/atus_subset.csv", StandardCharsets.UTF_8, ';')))), "Highest level of school completed", "./data/atus.csv", 0.0, new int[] { 0, 0, 0, 0, 0, 0, 0, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPrecomputedEntropyMetric(0.1d, false)).addCriterion(new FinancialProsecutorNoAttackPrivacy()), "Highest level of school completed", "./data/atus.csv", 2648409.8742651404, new int[] { 0, 3, 0, 1, 1, 1, 2, 1 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialProsecutorPrivacy()), "Highest level of school completed", "./data/atus.csv", 1.44033E7, new int[] { 0, 0, 0, 0, 0, 0, 0, 0 }, false) },
                                              /* 10 */{ new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createDiscernabilityMetric(true)).addCriterion(new FinancialProsecutorNoAttackPrivacy()), "Highest level of school completed", "./data/atus.csv", 6.820701679E9, new int[] { 0, 5, 0, 2, 1, 1, 2, 2 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createDiscernabilityMetric(true)).addCriterion(new FinancialJournalistPrivacy(DataSubset.create(Data.create("./data/atus.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/atus_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig3()), "Highest level of school completed", "./data/atus.csv", 5.7646609E7, new int[] { 0, 5, 0, 2, 1, 1, 2, 1 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createEntropyBasedInformationLossMetric()).addCriterion(new FinancialProsecutorNoAttackPrivacy()), "Highest level of school completed", "./data/atus.csv", 111422.02307271949, new int[] { 0, 3, 0, 0, 0, 0, 0, 1 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig3()), "Highest level of school completed", "./data/atus.csv", 4.0953114859107286E7, new int[] { 0, 0, 1, 2, 2, 2, 2, 1 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createLossMetric()).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/atus.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/atus_subset.csv", StandardCharsets.UTF_8, ';')))), "Highest level of school completed", "./data/atus.csv", 0.20610970617011581, new int[] { 1, 4, 0, 0, 2, 0, 1, 1 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createDiscernabilityMetric(true)).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig1()), "Highest level of school completed", "./data/atus.csv", 2.241709429E9, new int[] { 0, 0, 0, 0, 0, 0, 0, 0 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialProsecutorNoAttackPrivacy()).setFinancialConfiguration(getConfig3()), "Highest level of school completed", "./data/atus.csv", 1.0594182696792945E7, new int[] { 2, 2, 0, 0, 0, 0, 0, 1 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createLossMetric()).addCriterion(new FinancialJournalistPrivacy(DataSubset.create(Data.create("./data/atus.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/atus_subset.csv", StandardCharsets.UTF_8, ';')))), "Highest level of school completed", "./data/atus.csv", 0.0, new int[] { 0, 0, 0, 0, 0, 0, 0, 0 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createDiscernabilityMetric(true)).addCriterion(new FinancialProsecutorNoAttackPrivacy()).setFinancialConfiguration(getConfig2()), "RAMNTALL", "./data/cup.csv", 63457.0, new int[] { 0, 0, 0, 0, 0, 0, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createEntropyBasedInformationLossMetric()).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig4()), "RAMNTALL", "./data/cup.csv", 39707.30534178496, new int[] { 3, 4, 0, 2, 0, 2, 1 }, false) },
                                              /* 20 */{ new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createEntropyBasedInformationLossMetric()).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/cup.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/cup_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig3()), "RAMNTALL", "./data/cup.csv", 3391.1490869263093, new int[] { 3, 4, 1, 2, 0, 1, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPrecomputedEntropyMetric(0.1d, true)).addCriterion(new FinancialJournalistPrivacy(DataSubset.create(Data.create("./data/cup.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/cup_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig2()), "RAMNTALL", "./data/cup.csv", 117650.3776118251, new int[] { 2, 4, 0, 1, 0, 3, 2 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createLossMetric()).addCriterion(new FinancialProsecutorNoAttackPrivacy()).setFinancialConfiguration(getConfig1()), "RAMNTALL", "./data/cup.csv", 0.8114473285278123, new int[] { 5, 4, 1, 0, 1, 4, 4 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createEntropyBasedInformationLossMetric()).addCriterion(new FinancialJournalistPrivacy(DataSubset.create(Data.create("./data/cup.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/cup_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig4()), "RAMNTALL", "./data/cup.csv", 3882.50773654747, new int[] { 3, 4, 1, 2, 0, 2, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createPrecomputedEntropyMetric(0.1d, true)).addCriterion(new FinancialProsecutorNoAttackPrivacy()), "RAMNTALL", "./data/cup.csv", 2032837.6390798881, new int[] { 5, 4, 1, 0, 1, 4, 4 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createLossMetric()).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig1()), "RAMNTALL", "./data/cup.csv", 0.0, new int[] { 0, 0, 0, 0, 0, 0, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createDiscernabilityMetric(true)).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/cup.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/cup_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig1()), "RAMNTALL", "./data/cup.csv", 1445394.0, new int[] { 4, 4, 0, 1, 1, 2, 1 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createEntropyBasedInformationLossMetric()).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig1()), "RAMNTALL", "./data/cup.csv", 0.0, new int[] { 0, 0, 0, 0, 0, 0, 0 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPrecomputedEntropyMetric(0.1d, true)).addCriterion(new FinancialProsecutorNoAttackPrivacy()), "RAMNTALL", "./data/cup.csv", 1482557.7957185348, new int[] { 3, 4, 0, 2, 0, 2, 2 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPrecomputedEntropyMetric(0.1d, true)).addCriterion(new FinancialJournalistPrivacy(DataSubset.create(Data.create("./data/cup.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/cup_subset.csv", StandardCharsets.UTF_8, ';')))), "RAMNTALL", "./data/cup.csv", 0.0, new int[] { 0, 0, 0, 0, 0, 0, 0 }, true) },
                                              /* 30 */{ new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPrecomputedEntropyMetric(0.1d, true)).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/fars.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/fars_subset.csv", StandardCharsets.UTF_8, ';')))), "istatenum", "./data/fars.csv", 92927.5174540465, new int[] { 3, 0, 2, 3, 0, 1, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createPrecomputedEntropyMetric(0.1d, true)).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig1()), "istatenum", "./data/fars.csv", 0.0, new int[] { 0, 0, 0, 0, 0, 0, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialProsecutorNoAttackPrivacy()).setFinancialConfiguration(getConfig3()), "istatenum", "./data/fars.csv", 3919009.5013003163, new int[] { 2, 0, 0, 3, 0, 0, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createEntropyBasedInformationLossMetric()).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig2()), "istatenum", "./data/fars.csv", 29404.284086440723, new int[] { 1, 0, 0, 3, 0, 0, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/fars.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/fars_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig3()), "istatenum", "./data/fars.csv", 751449.1770133902, new int[] { 5, 2, 0, 0, 1, 2, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createPrecomputedEntropyMetric(0.1d, true)).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig3()), "istatenum", "./data/fars.csv", 1201007.0880104562, new int[] { 0, 2, 3, 3, 1, 2, 2 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createLossMetric()).addCriterion(new FinancialProsecutorNoAttackPrivacy()).setFinancialConfiguration(getConfig4()), "istatenum", "./data/fars.csv", 0.18571390635965068, new int[] { 4, 0, 2, 1, 0, 0, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d,Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialJournalistPrivacy(DataSubset.create(Data.create("./data/fars.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/fars_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig4()), "istatenum", "./data/fars.csv", 822265.8740269239, new int[] { 5, 2, 2, 0, 1, 2, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPrecomputedEntropyMetric(0.1d, true)).addCriterion(new FinancialProsecutorNoAttackPrivacy()).setFinancialConfiguration(getConfig1()), "istatenum", "./data/fars.csv", 1177149.7949303142, new int[] { 3, 2, 1, 2, 1, 2, 0 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createEntropyBasedInformationLossMetric()).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig4()), "istatenum", "./data/fars.csv", 44194.12520931992, new int[] { 5, 0, 0, 3, 0, 0, 0 }, true) },
                                              /* 40 */{ new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPrecomputedEntropyMetric(0.1d, false)).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/fars.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/fars_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig3()), "istatenum", "./data/fars.csv", 61748.334139460705, new int[] { 0, 0, 3, 3, 0, 0, 0 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPrecomputedEntropyMetric(0.1d, false)).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig2()), "istatenum", "./data/fars.csv", 548604.0354363323, new int[] { 0, 0, 2, 3, 0, 0, 0 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialProsecutorNoAttackPrivacy()).setFinancialConfiguration(getConfig2()), "EDUC", "./data/ihis.csv", 3.2284630088394594E8, new int[] { 0, 2, 0, 0, 1, 0, 1, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialJournalistPrivacy(DataSubset.create(Data.create("./data/ihis.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/ihis_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig2()), "EDUC", "./data/ihis.csv", 5.126858826257013E7, new int[] { 0, 2, 2, 1, 2, 0, 1, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPrecomputedEntropyMetric(0.1d, true)).addCriterion(new FinancialProsecutorNoAttackPrivacy()).setFinancialConfiguration(getConfig3()), "EDUC", "./data/ihis.csv", 5339062.508844286, new int[] { 0, 0, 0, 3, 0, 1, 0, 1 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createEntropyBasedInformationLossMetric()).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig3()), "EDUC", "./data/ihis.csv", 297107.71409336117, new int[] { 0, 2, 2, 2, 0, 0, 1, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createDiscernabilityMetric(true)).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/ihis.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/ihis_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig2()), "EDUC", "./data/ihis.csv", 1993662.0, new int[] { 0, 0, 2, 3, 0, 2, 0, 1 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/ihis.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/ihis_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig1()), "EDUC", "./data/ihis.csv", 8.993970011390184E7, new int[] { 0, 0, 0, 3, 4, 2, 0, 1 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialProsecutorNoAttackPrivacy()).setFinancialConfiguration(getConfig1()), "EDUC", "./data/ihis.csv", 6.388610340766058E8, new int[] { 1, 2, 2, 1, 2, 0, 1, 0 }, false) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.0d, Metric.createPrecomputedEntropyMetric(0.1d, true)).addCriterion(new FinancialJournalistPrivacy(DataSubset.create(Data.create("./data/ihis.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/ihis_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig4()), "EDUC", "./data/ihis.csv", 1188661.4931264082, new int[] { 0, 1, 2, 3, 0, 2, 1, 1 }, false) },                                              
                                              /* 50 */{ new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialProsecutorNoAttackPrivacy()).setFinancialConfiguration(getConfig2()), "EDUC", "./data/ihis.csv", 3.5652925691204834E8, new int[] { 0, 2, 2, 2, 0, 0, 1, 0 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createDiscernabilityMetric(true)).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig2()), "EDUC", "./data/ihis.csv", 3.1317776E7, new int[] { 0, 0, 0, 2, 0, 1, 0, 1 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(1d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialJournalistNoAttackPrivacy(DataSubset.create(Data.create("./data/ihis.csv", StandardCharsets.UTF_8, ';'), Data.create("./data/ihis_subset.csv", StandardCharsets.UTF_8, ';')))).setFinancialConfiguration(getConfig3()), "EDUC", "./data/ihis.csv", 1.3085931288759502E7, new int[] { 5, 0, 0, 1, 4, 0, 0, 0 }, true) },
                                              { new ARXAnonymizationTestCase(ARXConfiguration.create(0.04d, Metric.createPublisherPayoutMetric(false)).addCriterion(new FinancialProsecutorPrivacy()).setFinancialConfiguration(getConfig1()), "EDUC", "./data/ihis.csv", 1.203978E8, new int[] { 0, 0, 0, 0, 0, 0, 0, 0 }, true) },
        });
    }
    
    /**
     * Returns a configuration
     * @return
     */
    private static ARXFinancialConfiguration getConfig1() {
        return ARXFinancialConfiguration.create()
                .setAdversaryCost(2d)
                .setAdversaryGain(1200d)
                .setPublisherLoss(300d)
                .setPublisherBenefit(1200d);
    }

    /**
     * Returns a configuration
     * @return
     */
    private static ARXFinancialConfiguration getConfig2() {
        return ARXFinancialConfiguration.create()
                .setAdversaryCost(20d)
                .setAdversaryGain(120d)
                .setPublisherLoss(3000d)
                .setPublisherBenefit(1200d);
    }
    
    /**
     * Returns a configuration
     * @return
     */
    private static ARXFinancialConfiguration getConfig3() {
        return ARXFinancialConfiguration.create()
                .setAdversaryCost(20d)
                .setAdversaryGain(120d)
                .setPublisherLoss(3000d)
                .setPublisherBenefit(120d);
    }

    /**
     * Returns a configuration
     * @return
     */
    private static ARXFinancialConfiguration getConfig4() {
        return ARXFinancialConfiguration.create()
                .setAdversaryCost(20d)
                .setAdversaryGain(1000d)
                .setPublisherLoss(3000d)
                .setPublisherBenefit(120d);
    }
    /**
     * Creates a new instance
     * 
     * @param testCase
     */
    public TestAnonymizationFinancialPrivacy(final ARXAnonymizationTestCase testCase) {
        super(testCase);
    }
}
