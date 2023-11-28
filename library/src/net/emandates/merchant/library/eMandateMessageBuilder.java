
package net.emandates.merchant.library;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import jakarta.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

class eMandateMessageBuilder {
    private final Instrumentation localInstrumentCode;
    
    public eMandateMessageBuilder(Instrumentation localInstrumentCode) {
        this.localInstrumentCode = localInstrumentCode;
    }
    
    private void verifyMaxAmount(Double maxAmount) throws CommunicatorException {
        if (maxAmount == null)
            return;
        if (localInstrumentCode == Instrumentation.B2B) {
            if (Math.abs(maxAmount) < 2*Double.MIN_VALUE) {
                throw new CommunicatorException("MaxAmount can't be 0");
            }
            DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
            df.setGroupingUsed(false);
            String val = df.format(maxAmount);
            if ((val.indexOf('.') > 0) && (val.indexOf('.') < val.length() - 3)) { // length()-1 = actual last character, 2 last digits allowed
                throw new CommunicatorException("no more than 2 decimal places allowed for MaxAmount");
            }
            val = val.replace(".", "");
            if (val.length() > 11) {
                throw new CommunicatorException("MaxAmount should have maximum 11 digits (excluding decimal separator)");
            }
        }
    }
    
    public Element getNewMandate(final NewMandateRequest newMandateRequest) throws ParserConfigurationException, SAXException, IOException, DatatypeConfigurationException, JAXBException, CommunicatorException {
        verifyMaxAmount(newMandateRequest.getMaxAmount());
        
        schemas.pain009.Document eMandate = new schemas.pain009.Document();
        eMandate.setMndtInitnReq(new schemas.pain009.MandateInitiationRequestV04() {{
            setGrpHdr(new schemas.pain009.GroupHeader47() {{
                setCreDtTm(Utils.UtcNow());
                setMsgId(newMandateRequest.getMessageID());
            }});
            getMndt().add(new schemas.pain009.Mandate7() {{
                setMndtId(newMandateRequest.geteMandateID());
                setMndtReqId("NOTPROVIDED");
                setTp(new schemas.pain009.MandateTypeInformation1() {{
                    setSvcLvl(new schemas.pain009.ServiceLevel8Choice() {{
                        setCd("SEPA");
                    }});
                    setLclInstrm(new schemas.pain009.LocalInstrument2Choice() {{
                        setCd(localInstrumentCode.name());
                    }});
                }});
                setOcrncs(new schemas.pain009.MandateOccurrences3() {{
                    setSeqTp((schemas.pain009.SequenceType2Code.valueOf(newMandateRequest.getSequenceType().name())));
                }});
                setMaxAmt(localInstrumentCode == Instrumentation.B2B && newMandateRequest.getMaxAmount() != null? new schemas.pain009.ActiveCurrencyAndAmount() {{
                    setCcy("EUR");
                    setValue(BigDecimal.valueOf(newMandateRequest.getMaxAmount()).setScale(2));
                }} : null);
                if (newMandateRequest.geteMandateReason()!= null && !newMandateRequest.geteMandateReason().isEmpty())
                    setRsn(new schemas.pain009.MandateSetupReason1Choice() {{
                        setPrtry(newMandateRequest.geteMandateReason());
                    }});
                setCdtr(new schemas.pain009.PartyIdentification43());
                setDbtr(new schemas.pain009.PartyIdentification43() {{
                    if (newMandateRequest.getDebtorReference() != null && !newMandateRequest.getDebtorReference().isEmpty()) {
                        setId(new schemas.pain009.Party11Choice() {{
                            setPrvtId(new schemas.pain009.PersonIdentification5() {{
                                getOthr().add(new schemas.pain009.GenericPersonIdentification1() {{
                                    setId(newMandateRequest.getDebtorReference());
                                }});
                            }});
                        }});
                    }
                }});
                setDbtrAgt(new schemas.pain009.BranchAndFinancialInstitutionIdentification5() {{
                    setFinInstnId(new schemas.pain009.FinancialInstitutionIdentification8() {{
                        setBICFI(newMandateRequest.getDebtorBankID());
                    }});
                }});
                if (newMandateRequest.getPurchaseID()!= null && !newMandateRequest.getPurchaseID().isEmpty())
                    getRfrdDoc().add(new schemas.pain009.ReferredDocumentInformation6() {{
                        setTp(new schemas.pain009.ReferredDocumentType4() {{
                            setCdOrPrtry(new schemas.pain009.ReferredDocumentType3Choice() {{
                                setPrtry(newMandateRequest.getPurchaseID());
                            }});
                        }});
                    }});
            }});
        }});

        String mandateString = Utils.serialize(eMandate, schemas.pain009.Document.class);

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(mandateString.getBytes(StandardCharsets.UTF_8)), "");
        //doc.getDocumentElement().setAttribute("xmlns", "urn:iso:std:iso:20022:tech:xsd:pain.009.001.04");

        return doc.getDocumentElement();
    }
    
    public Element getAmend(final AmendmentRequest amendmentRequest) throws ParserConfigurationException, SAXException, IOException, DatatypeConfigurationException, JAXBException, CommunicatorException {
        
        schemas.pain010.Document eMandate = new schemas.pain010.Document();
        eMandate.setMndtAmdmntReq(new schemas.pain010.MandateAmendmentRequestV04() {{
            setGrpHdr(new schemas.pain010.GroupHeader47() {{
                setMsgId(amendmentRequest.getMessageID());
                setCreDtTm(Utils.UtcNow());
            }});
            getUndrlygAmdmntDtls().add(new schemas.pain010.MandateAmendment4() {{
                setAmdmntRsn(new schemas.pain010.MandateAmendmentReason1() {{
                    setRsn(new schemas.pain010.MandateReason1Choice() {{
                        setCd("MD16");
                    }});
                }});
                setOrgnlMndt(new schemas.pain010.OriginalMandate3Choice() {{
                    setOrgnlMndt(new schemas.pain010.Mandate5() {{
                        setMndtId(amendmentRequest.geteMandateID());
                        setCdtr(new schemas.pain010.PartyIdentification43());
                        setDbtr(new schemas.pain010.PartyIdentification43());
                        setDbtrAcct(new schemas.pain010.CashAccount24() {{
                            setId(new schemas.pain010.AccountIdentification4Choice() {{
                                setIBAN(amendmentRequest.getOriginalIBAN());
                            }});
                        }});
                        setDbtrAgt(new schemas.pain010.BranchAndFinancialInstitutionIdentification5() {{
                            setFinInstnId(new schemas.pain010.FinancialInstitutionIdentification8() {{
                                setBICFI(amendmentRequest.getOriginalDebtorBankID());
                            }});
                        }});
                    }});
                }});
                setMndt(new schemas.pain010.Mandate6() {{
                    setMndtId(amendmentRequest.geteMandateID());
                    setMndtReqId("NOTPROVIDED");
                    setTp(new schemas.pain010.MandateTypeInformation1() {{
                        setSvcLvl(new schemas.pain010.ServiceLevel8Choice() {{
                            setCd("SEPA");
                        }});
                        setLclInstrm(new schemas.pain010.LocalInstrument2Choice() {{
                            setCd(localInstrumentCode.name());
                        }});
                    }});
                    setOcrncs(new schemas.pain010.MandateOccurrences3() {{
                        setSeqTp((schemas.pain010.SequenceType2Code.valueOf(amendmentRequest.getSequenceType().name())));
                        // TODO: add frequency period
                    }});
                    
                    
                    if (amendmentRequest.geteMandateReason()!= null && !amendmentRequest.geteMandateReason().isEmpty())
                        setRsn(new schemas.pain010.MandateSetupReason1Choice() {{
                            setPrtry(amendmentRequest.geteMandateReason());
                        }});
                    setCdtr(new schemas.pain010.PartyIdentification43());
                    setDbtr(new schemas.pain010.PartyIdentification43() {{
                        if (amendmentRequest.getDebtorReference() != null && !amendmentRequest.getDebtorReference().isEmpty()) {
                            setId(new schemas.pain010.Party11Choice() {{
                                setPrvtId(new schemas.pain010.PersonIdentification5() {{
                                    getOthr().add(new schemas.pain010.GenericPersonIdentification1() {{
                                        setId(amendmentRequest.getDebtorReference());
                                    }});
                                }});
                            }});
                        }
                    }});
                    setDbtrAgt(new schemas.pain010.BranchAndFinancialInstitutionIdentification5() {{
                        setFinInstnId(new schemas.pain010.FinancialInstitutionIdentification8() {{
                            setBICFI(amendmentRequest.getDebtorBankID());
                        }});
                    }});
                    if (amendmentRequest.getPurchaseID()!= null && !amendmentRequest.getPurchaseID().isEmpty())
                        getRfrdDoc().add(new schemas.pain010.ReferredDocumentInformation6() {{
                            setTp(new schemas.pain010.ReferredDocumentType4() {{
                                setCdOrPrtry(new schemas.pain010.ReferredDocumentType3Choice() {{
                                    setPrtry(amendmentRequest.getPurchaseID());
                                }});
                            }});
                        }});
                }});
            }});
        }});

        String mandateString = Utils.serialize(eMandate, schemas.pain010.Document.class);

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(mandateString.getBytes(StandardCharsets.UTF_8)), "");
        //doc.getDocumentElement().setAttribute("xmlns", "urn:iso:std:iso:20022:tech:xsd:pain.010.001.04");

        return doc.getDocumentElement();
    }
    
    public Element getCancel(final CancellationRequest cancellationRequest) throws ParserConfigurationException, SAXException, IOException, DatatypeConfigurationException, JAXBException, CommunicatorException {
        verifyMaxAmount(cancellationRequest.getMaxAmount());
        
        schemas.pain011.Document eMandate = new schemas.pain011.Document();
        eMandate.setMndtCxlReq(new schemas.pain011.MandateCancellationRequestV04() {{
            setGrpHdr(new schemas.pain011.GroupHeader47() {{
                setMsgId(cancellationRequest.getMessageID());
                setCreDtTm(Utils.UtcNow());
            }});
            getUndrlygCxlDtls().add(new schemas.pain011.MandateCancellation4() {{
                setCxlRsn(new schemas.pain011.PaymentCancellationReason1() {{
                    setRsn(new schemas.pain011.MandateReason1Choice() {{
                        setCd("MD16");
                    }});
                }});
                setOrgnlMndt(new schemas.pain011.OriginalMandate3Choice() {{
                    setOrgnlMndt(new schemas.pain011.Mandate5() {{
                        setMndtId(cancellationRequest.geteMandateID());
                        setMndtReqId("NOTPROVIDED");
                        setTp(new schemas.pain011.MandateTypeInformation1() {{
                            setSvcLvl(new schemas.pain011.ServiceLevel8Choice() {{
                                setCd("SEPA");
                            }});
                            setLclInstrm(new schemas.pain011.LocalInstrument2Choice() {{
                                setCd(localInstrumentCode.name());
                            }});
                        }});
                        setOcrncs(new schemas.pain011.MandateOccurrences3() {{
                            setSeqTp((schemas.pain011.SequenceType2Code.valueOf(cancellationRequest.getSequenceType().name())));
                            // TODO: add frequency period
                        }});
                        setMaxAmt(cancellationRequest.getMaxAmount() != null? new schemas.pain011.ActiveOrHistoricCurrencyAndAmount() {{
                            setCcy("EUR");
                            setValue(BigDecimal.valueOf(cancellationRequest.getMaxAmount()).setScale(2));
                        }} : null);
                        if (cancellationRequest.geteMandateReason()!= null && !cancellationRequest.geteMandateReason().isEmpty())
                            setRsn(new schemas.pain011.MandateSetupReason1Choice() {{
                                setPrtry(cancellationRequest.geteMandateReason());
                            }});
                        setCdtr(new schemas.pain011.PartyIdentification43());
                        setDbtr(new schemas.pain011.PartyIdentification43() {{
                            if (cancellationRequest.getDebtorReference() != null && !cancellationRequest.getDebtorReference().isEmpty()) {
                                setId(new schemas.pain011.Party11Choice() {{
                                    setPrvtId(new schemas.pain011.PersonIdentification5() {{
                                        getOthr().add(new schemas.pain011.GenericPersonIdentification1() {{
                                            setId(cancellationRequest.getDebtorReference());
                                        }});
                                    }});
                                }});
                            }
                        }});
                        setDbtrAcct(new schemas.pain011.CashAccount24() {{
                            setId(new schemas.pain011.AccountIdentification4Choice() {{
                                setIBAN(cancellationRequest.getOriginalIBAN());
                            }});
                        }});
                        setDbtrAgt(new schemas.pain011.BranchAndFinancialInstitutionIdentification5() {{
                            setFinInstnId(new schemas.pain011.FinancialInstitutionIdentification8() {{
                                setBICFI(cancellationRequest.getDebtorBankID());
                            }});
                        }});
                        if (cancellationRequest.getPurchaseID()!= null && !cancellationRequest.getPurchaseID().isEmpty())
                            getRfrdDoc().add(new schemas.pain011.ReferredDocumentInformation6() {{
                                setTp(new schemas.pain011.ReferredDocumentType4() {{
                                    setCdOrPrtry(new schemas.pain011.ReferredDocumentType3Choice() {{
                                        setPrtry(cancellationRequest.getPurchaseID());
                                    }});
                                }});
                            }});
                    }});
                }});
            }});
        }});
        
        String mandateString = Utils.serialize(eMandate, schemas.pain011.Document.class);

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(mandateString.getBytes(StandardCharsets.UTF_8)), "");
        //doc.getDocumentElement().setAttribute("xmlns", "urn:iso:std:iso:20022:tech:xsd:pain.010.001.04");

        return doc.getDocumentElement();
    }
}
