package client;

import com.example.grpc.StreamSumGrpc;
import com.example.grpc.Sum.*;
import io.grpc.stub.StreamObserver;

/**
 * Stream Sum class, it extends the boilerplate generated by the GRPC framework for Java.
 */
public class StreamSum extends StreamSumGrpc.StreamSumImplBase {
    /**
     * Implementation of the Stream Sum method, it uses a stream, thus it does not need to be called every single time with the arguments for the sum (they will be written on the stream).
     * @param responseObserver This is actually the stream observer for the response
     * @return It returns the stream observer for the request, so that the caller can actually create events for the callee and request a sum.
     */
    @Override
    public StreamObserver<StreamSumRequest>
    streamStreamSum(StreamObserver<StreamSumResponse> responseObserver) {
        return new StreamObserver<StreamSumRequest>() {
            @Override
            public void onNext(StreamSumRequest value) {
                StreamSumResponse response = StreamSumResponse.newBuilder()
                        .setSum(value.getN1() + value.getN2())
                        .build();

                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.print("Ciao");
            }
        };
    }
}
