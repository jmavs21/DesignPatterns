package structural.decorator;

public class Exercise {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.openProject("...");
    }
}

class Editor {
    public void openProject(String path) {
        IArtifact[] artifacts = {
                new Artifact("Main"),
                new Artifact("Demo"),
                new Artifact("EmailClient"),
                new Artifact("EmailProvider"),
        };
        artifacts[0] = new ErrorDecorator(new MainDecorator(artifacts[0]));
        artifacts[2] = new ErrorDecorator(artifacts[2]);
        for (var artifact : artifacts)
            System.out.println(artifact.render());
    }
}

interface IArtifact {
    String render();
}

class Artifact implements IArtifact {
    private String name;
    public Artifact(String name) {
        this.name = name;
    }
    @Override
    public String render() {
        return name;
    }
}

class MainDecorator implements IArtifact {
    private IArtifact artifact;
    public MainDecorator(IArtifact artifact) {
        this.artifact = artifact;
    }
    @Override
    public String render() {
        return artifact.render() + " [Main]";
    }
}

class ErrorDecorator implements IArtifact {
    private IArtifact artifact;
    protected ErrorDecorator(IArtifact artifact) {
        this.artifact = artifact;
    }
    @Override
    public String render() {
        var mainIcon = artifact.render();
        return mainIcon + " [Error]";
    }
}
