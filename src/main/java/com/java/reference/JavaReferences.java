package com.java.reference;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.info.GraphPathRecord;
import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.lang.ref.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.SortedSet;

public class JavaReferences {
    private final ReferenceQueue<ReferencedObject> referencedObjectReferenceQueue;

    //public static final VirtualMachine VM = org.openjdk.jol.vm.VM.current();


    private ReferencedObject referenceObj = new ReferencedObject();
    private WeakReference<ReferencedObject> weakReference = new WeakReference<>(null);
    private SoftReference<ReferencedObject> softReference = new SoftReference<>(null);
    private PhantomReference<ReferencedObject> phantomReference;

    public JavaReferences() {
        //weakReference = new WeakReference<>(referenceObj);
        //softReference = new SoftReference<>(referenceObj);
        referencedObjectReferenceQueue = new ReferenceQueue<>();
        phantomReference = new PhantomReference<>(referenceObj, referencedObjectReferenceQueue);
    }


    public ReferencedObject getReferenceObj() {
        return referenceObj;
    }

    public void setReferenceObj(ReferencedObject referenceObj) {
        this.referenceObj = referenceObj;
    }


    private static class ReferencedObject {

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        //System.out.println(VM.details());
        JavaReferences javaReferences = new JavaReferences();
        //javaReferences.deletePngFilesInDir();

       /* final GraphLayout graphLayout = GraphLayout.parseInstance(javaReferences);
        final SortedSet<Long> addresses = graphLayout.addresses();*/

        /* addresses.stream().map(graphLayout::record).map(GraphPathRecord::size).forEach(System.out::println);
         */
        /* javaReferences.printObjectLayout();*/

        javaReferences.setReferenceObj(null);


        //Thread.sleep(120000);
        System.gc();
        System.out.println(javaReferences.getReferenceObj());
        System.out.println(javaReferences.weakReference.get());
        System.out.println(javaReferences.softReference.get());
        System.out.println(javaReferences.phantomReference.get());
        //System.out.println(javaReferences.referencedObjectReferenceQueue.poll());

        Reference<? extends ReferencedObject> poll;
        final ReferenceQueue<ReferencedObject> referencedObjectReferenceQueue = javaReferences.referencedObjectReferenceQueue;
        javaReferences = null;
        Instant start = Instant.now();
        while ((poll = referencedObjectReferenceQueue.poll()) == null) {
            if (Duration.between(start, Instant.now()).toSeconds() > 60) {
                System.gc();
                System.out.println("Garbage Collected...");
                start = Instant.now();
            }
        }
        if (null != poll)
            System.out.println(poll);
        /* javaReferences.printObjectLayout();*/

    }

    private void printObjectLayout() {
        System.out.println(GraphLayout.parseInstance(this).addresses());
        try {
            GraphLayout.parseInstance(this).toImage("c:\\Work\\Test" + Instant.now().toEpochMilli() + ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deletePngFilesInDir() throws IOException {
        Files.list(Paths.get("C:\\Work\\")).filter(path -> path.getFileName().toString().endsWith(".png")).forEach(path -> {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void isReferencePresent(Object checkReferenceValidity) {
        //log(VM.addressOf(checkReferenceValidity));
    }

    private static void log(Object details) {
        System.out.println(details);
    }

    public void strongReference() throws InterruptedException {
        sleep(1);
        System.gc();
        printUsedMemory();
        log("Object Created...");
        CheckReferenceValidity obj = new CheckReferenceValidity();

        printUsedMemory();

        obj = null;
        log("Nullifying the reference");
        sleep(10000);
        log("About to run GC");
        System.gc();

        printUsedMemory();
    }

    public void weakReference() throws InterruptedException {
        sleep(10000);
        System.gc();
        printUsedMemory();
        log("Object Created...");
        CheckReferenceValidity obj = new CheckReferenceValidity();

        isReferencePresent(obj);


        WeakReference<CheckReferenceValidity> checkReferenceValidityWeakReference = new WeakReference<>(obj);
        logReference(checkReferenceValidityWeakReference);
        printUsedMemory();


        isReferencePresent(checkReferenceValidityWeakReference);
        obj = null;
        logReference(checkReferenceValidityWeakReference);
        log("Nullifying the reference");

        System.gc();
        sleep(100000);
        log("About to run GC");
        logReference(checkReferenceValidityWeakReference);

        System.out.println(checkReferenceValidityWeakReference.isEnqueued());

        printUsedMemory();
    }

    private void logReference(WeakReference<CheckReferenceValidity> checkReferenceValidityWeakReference) {
        log(GraphLayout.parseInstance(checkReferenceValidityWeakReference).toPrintable());
    }


    private void sleep(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    private void printUsedMemory() {
        System.out.println(MemoryUtil.getUsedMemory());
    }

    private static class MemoryUtil {

        public static final Runtime RUNTIME = Runtime.getRuntime();

        public static Memory getUsedMemory() {
            return Memory.ofBytes(RUNTIME.totalMemory() - RUNTIME.freeMemory());
        }
    }

    private static class CheckReferenceValidity {
    }
}
