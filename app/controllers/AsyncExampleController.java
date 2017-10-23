package controllers;

import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AsyncExampleController extends Controller {
    private HttpExecutionContext httpExecutionContext;

    @Inject
    public AsyncExampleController(HttpExecutionContext ec) {
        this.httpExecutionContext = ec;
    }

    public CompletionStage<Result> index() {
        // Use a different task with explicit EC
        return calculateResponse().thenApplyAsync(answer -> {
            // uses Http.Context
            ctx().flash().put("info", "Response updated!");
            return ok("answer was " + answer);
        }, httpExecutionContext.current());
    }

    private static CompletionStage<Integer> calculateResponse() {
        return CompletableFuture.completedFuture(new Random().nextInt());
    }
}